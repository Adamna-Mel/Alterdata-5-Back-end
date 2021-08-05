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

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.alterdata.pack.exception.BadRequestException;
import br.com.alterdata.pack.exception.NotFoundException;
import br.com.alterdata.pack.exception.UnsupportedMediaTypeException;
import br.com.alterdata.pack.model.Equipe;
import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.repository.EquipeRepository;
import br.com.alterdata.pack.shared.EquipeDto;

@Service
public class EquipeServiceImpl implements EquipeService{
    
    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/java/br/com/alterdata/pack/images/equipe";

    @Autowired
    private EquipeRepository _repositorioEquipe;

    //#region GET

    @Override
    public List<EquipeDto> obterTodos(Pageable pageable) {
        Page<Equipe> equipes = _repositorioEquipe.findAll(pageable);
        
        return equipes.stream().map(equipe -> new ModelMapper().map(equipe, EquipeDto.class))
                      .collect(Collectors.toList());
    }

    @Override
    public Optional<EquipeDto> obterPorId(Long id) {
        Optional<Equipe> encontrado = _repositorioEquipe.findByIdEquipe(id);

        ModelMapper mapper = new ModelMapper();

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
    

    @Override
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
	public byte[] retornarAvatar(Long id) throws IOException {

		Optional<Equipe> encontrado = _repositorioEquipe.findById(id);
        
        if (!encontrado.isPresent()) {
            throw new NotFoundException("Não foi encontrado nenhuma equipe com o Id: " + id);
        }
		if(!encontrado.get().getAvatarName().equals("")){
        
		    File imagemArquivo = new File(uploadDirectory + "/" + encontrado.get().getAvatarName());
		
			return Files.readAllBytes(imagemArquivo.toPath());			
        }
		throw new NotFoundException("Imagem não encontrada na equipe com ID: ");
	}

    //#endregion
    //#region POST
    
    @Override
    public Equipe criarEquipe(EquipeDto equipeDto) {
    
        ModelMapper mapper = new ModelMapper();

        Equipe equipe = mapper.map(equipeDto, Equipe.class);

        equipe.setIdEquipe(null);

        verificarSeEquipeExiste(equipe);

		equipe.setAvatarName("");

        if (equipe.getNome() == "" || equipe.getNome() == null) {
            throw new BadRequestException("Nome não pode ser nulo!");
        }
    
        return  _repositorioEquipe.save(equipe);
    }
    
    //#endregion
    //#region PUT

    @Override
    public Equipe atualizar(Long id, EquipeDto equipeDto) {

        Optional<Equipe> encontrado = _repositorioEquipe.findById(id);

        ModelMapper mapper = new ModelMapper();
        
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

    //#endregion
    //#region PATCH

    
    public Equipe editarAvatar(Long id, MultipartFile arquivo){
		UUID uuid = UUID.randomUUID();

        Optional<Equipe> equipe = _repositorioEquipe.findById(id);
        
        if (!equipe.isPresent()) {
            throw new NotFoundException("Não foi encontrado nenhuma equipe com o Id: " + id);
        }

        String formato = arquivo.getContentType();
		formato = formato.substring(6,formato.length());

		if (
			!formato.equals("png") & 
			!formato.equals("jpg") &
			!formato.equals("jpeg") &
			!formato.equals("gif")
		){
			throw new UnsupportedMediaTypeException("O formato da imagem não é suportado!");
		}
                
		String fileName = uuid + arquivo.getOriginalFilename();
		Path fileNamePath = Paths.get(uploadDirectory, fileName);

		try {
			Files.write(fileNamePath, arquivo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();;
		}
		
		File destino = new File(uploadDirectory, equipe.get().getAvatarName());

		if(!equipe.get().getAvatarName().equals("")){
			destino.delete();
	   } 

		equipe.get().setAvatarName(fileName);

		return _repositorioEquipe.save(equipe.get());
	}


    //#endregion
    //#region DELETE

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

        if(!encontrado.get().getAvatarName().equals("")){
			destino.delete();
	   }
	   
        encontrado.get().setMembros(null);
        
        this._repositorioEquipe.deleteById(id);
    }

    //#endregion
 
    private void verificarSeEquipeExiste(Equipe equipe) {
        Optional<Equipe> equipeExiste = _repositorioEquipe.findByNome(equipe.getNome());

        if (equipeExiste.isPresent()) {
            throw new BadRequestException("Opa! Já existe equipe com esse nome.");
        }
    }
       
}
