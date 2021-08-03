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

    @Override
    public List<EquipeDto> obterTodos(Pageable pageable) {
        Page<Equipe> equipes = _repositorioEquipe.findAll(pageable);
        
        return equipes.stream().map(equipe -> new ModelMapper().map(equipe, EquipeDto.class))
                      .collect(Collectors.toList());
    }


    @Override
    public Optional<EquipeDto> obterPorId(Long id) {
        Optional<Equipe> encontrado = _repositorioEquipe.findByIdEquipe(id);

        if(!encontrado.isPresent()) {
            throw new NotFoundException("Não foi encontrado equipe com o ID: " + id);
        }
        return Optional.of(new ModelMapper().map(encontrado.get(),EquipeDto.class));
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
    public Equipe criarEquipe(Equipe equipe, MultipartFile arquivo) {
        equipe.setIdEquipe(null);

        UUID uuid = UUID.randomUUID();

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
    
        Equipe novaEquipe = _repositorioEquipe.save(equipe);
        return novaEquipe;
    }
    

    @Override
    public Equipe atualizar(Long id, Equipe equipe) {
        Optional<Equipe> encontrado = _repositorioEquipe.findByIdEquipe(id);

        if(!encontrado.isPresent()) {
            throw new NotFoundException("Não foi encontrado equipe com o ID: " + id);
        }
        equipe.setIdEquipe(id);

        if(equipe.getNome() == "" || equipe.getNome() == null){
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
		Optional<EquipeDto> equipe = obterPorId(id);

		File imagemArquivo = new File(uploadDirectory + "/" + equipe.get().getAvatarName());
		
		if(!equipe.get().getAvatarName().equals(null) || ! equipe.get().getAvatarName().equals("")){
			return Files.readAllBytes(imagemArquivo.toPath());			
		}
		throw new NotFoundException("Imagem não encontrada na equipe com ID: " + equipe.get().getIdEquipe());
	}
    

    public Equipe editarAvatar(Long id, MultipartFile arquivo){
		UUID uuid = UUID.randomUUID();

        Equipe equipe = new Equipe();
		Optional<EquipeDto> equipeDto = obterPorId(id);
        BeanUtils.copyProperties(equipeDto, equipe);  

		String fileName = uuid + arquivo.getOriginalFilename();
		Path fileNamePath = Paths.get(uploadDirectory, fileName);

		try {
			Files.write(fileNamePath, arquivo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();;
		}
		
		File destino = new File(uploadDirectory, equipeDto.get().getAvatarName());

		try {
			destino.delete();
	   } catch (Exception e) {
		   throw new RuntimeException("Erro ao deletar imagem", e);
	   }

		equipeDto.get().setAvatarName(fileName);

		return _repositorioEquipe.save(equipe);
	}


    private void verificarSeEquipeExiste(Equipe equipe) {
        Optional<Equipe> equipeExiste = _repositorioEquipe.findByNome(equipe.getNome());

        if (equipeExiste.isPresent()) {
            throw new BadRequestException("Opa! Já existe equipe com esse nome.");
        }
    }
       
}
