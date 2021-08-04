package br.com.alterdata.pack.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.alterdata.pack.exception.BadRequestException;
import br.com.alterdata.pack.exception.NotFoundException;
import br.com.alterdata.pack.model.Equipe;
import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.repository.EquipeRepository;
import br.com.alterdata.pack.shared.EquipeDto;

@Service
public class EquipeServiceImpl implements EquipeService{
    
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/java/br/com/alterdata/pack/images/equipe";

    @Autowired
    private EquipeRepository _repositorioEquipe;

    ModelMapper mapper = new ModelMapper();

    @Override
    public List<EquipeDto> obterTodos(Pageable pageable) {
        Page<Equipe> equipes = _repositorioEquipe.findAll(pageable);
        
        return equipes.stream().map(equipe -> mapper.map(equipe, EquipeDto.class))
                      .collect(Collectors.toList());
    }


    @Override
    public Optional<EquipeDto> obterPorId(Long id) {
        Optional<Equipe> encontrado = _repositorioEquipe.findByIdEquipe(id);

        if(!encontrado.isPresent()) {
            throw new NotFoundException("Não foi encontrado equipe com o ID: " + id);
        }
        return Optional.of(mapper.map(encontrado.get(),EquipeDto.class));
    }

    @Override
    public List<Equipe> obterPorNome(String nome) {
        List<Equipe> encontrado = _repositorioEquipe.findByNomeContainingIgnoreCase(nome);

        if (encontrado.size() == 0) {
            throw new NotFoundException("Não foi encontrado equipe com o nome: " + nome);
        }
        return encontrado;
    }
    

    public List<Usuario> obterUsuariosPorLogin(Long idEquipe,String login) {
        
        Optional<Equipe> encontrado = _repositorioEquipe.findByIdEquipe(idEquipe);

        if (!encontrado.isPresent()) {
            throw new NotFoundException("Não foi encontrado nenhuma equipe com o Id: " + idEquipe);
        }

        List<Usuario> usuarios = encontrado.get().getMembros().stream()
                                                              .filter( usuario -> usuario.getLogin().contains(login))
                                                              .collect(Collectors.toList());

        if (usuarios.size() == 0) {
            throw new NotFoundException("Não foi encontrado nenhum usuario com o nome: " + login);
        }
        return usuarios;
    }

    @Override
    public Equipe criarEquipe(EquipeDto equipeDto, MultipartFile arquivo) {
    
        UUID uuid = UUID.randomUUID();

        Equipe equipe = mapper.map(equipeDto, Equipe.class);

        equipe.setIdEquipe(null);

        verificarSeEquipeExiste(equipe);

        String fileName = uuid + arquivo.getOriginalFilename();
		Path fileNamePath = Paths.get(uploadDirectory, fileName);

		try {
			Files.write(fileNamePath, arquivo.getBytes());

		} catch (IOException e) {
			e.printStackTrace();;
		}

		equipe.setAvatarName(fileName);

        if (equipe.getNome() == "" || equipe.getNome() == null) {
            throw new BadRequestException("Nome não pode ser nulo!");
        }
    
        return  _repositorioEquipe.save(equipe);
    }
    

    @Override
    public Equipe atualizar(Long id, EquipeDto equipeDto) {

        Optional<Equipe> encontrado = _repositorioEquipe.findById(id);
        
        if (!encontrado.isPresent()) {
            throw new NotFoundException("Não foi encontrado nenhuma equipe com o Id: " + id);
        }

        Equipe equipe = mapper.map(equipeDto, Equipe.class);

        equipe.setIdEquipe(id);
        equipe.setAvatarName(encontrado.get().getAvatarName());

        if(equipeDto.getNome() == "" || equipeDto.getNome() == null){
            throw new BadRequestException("Nome não pode ser nulo!");
        }

        Equipe equipeAtualizado = _repositorioEquipe.save(equipe);

        return equipeAtualizado;
    }


    @Override
    public void deletar(Long id) {
        Optional<Equipe> encontrado = _repositorioEquipe.findByIdEquipe(id);

        if (!encontrado.isPresent()) {
            throw new NotFoundException("Não existe equipe com o id informado: " + id);
        }
        for (Usuario membro : encontrado.get().getMembros()) {
            membro.setEquipe(null);
        }
        File destino = new File(uploadDirectory, encontrado.get().getAvatarName());

        try {
			destino.delete();
	   } catch (Exception e) {
		   throw new RuntimeException("Erro ao deletar imagem", e);
	   }
        encontrado.get().setMembros(null);
        
        this._repositorioEquipe.deleteById(id);
    }

    @Override
	public byte[] retornarAvatar(Long id) throws IOException {

		Optional<Equipe> encontrado = _repositorioEquipe.findById(id);
        
        if (!encontrado.isPresent()) {
            throw new NotFoundException("Não foi encontrado nenhuma equipe com o Id: " + id);
        }
        
        Equipe equipe = mapper.map(encontrado, Equipe.class);

		File imagemArquivo = new File(uploadDirectory + "/" + equipe.getAvatarName());
		
		if(!equipe.getAvatarName().equals(null) || ! equipe.getAvatarName().equals("")){
			return Files.readAllBytes(imagemArquivo.toPath());			
		}
		throw new NotFoundException("Imagem não encontrada na equipe com ID: ");
	}
    
    public Equipe editarAvatar(Long id, MultipartFile arquivo){
		UUID uuid = UUID.randomUUID();

        Optional<Equipe> equipe = _repositorioEquipe.findById(id);
        
        if (!equipe.isPresent()) {
            throw new NotFoundException("Não foi encontrado nenhuma equipe com o Id: " + id);
        }
                
		String fileName = uuid + arquivo.getOriginalFilename();
		Path fileNamePath = Paths.get(uploadDirectory, fileName);

		try {
			Files.write(fileNamePath, arquivo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();;
		}
		
		File destino = new File(uploadDirectory, equipe.get().getAvatarName());

		try {
			destino.delete();
	   } catch (Exception e) {
		   throw new RuntimeException("Erro ao deletar imagem", e);
	   }

		equipe.get().setAvatarName(fileName);

		return _repositorioEquipe.save(equipe.get());
	}


    private void verificarSeEquipeExiste(Equipe equipe) {
        Optional<Equipe> equipeExiste = _repositorioEquipe.findByNome(equipe.getNome());

        if (equipeExiste.isPresent()) {
            throw new BadRequestException("Opa! Já existe equipe com esse nome.");
        }
    }
       
}
