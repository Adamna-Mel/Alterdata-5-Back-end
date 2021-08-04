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
import br.com.alterdata.pack.model.Cargo;
import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.repository.CargoRepository;
import br.com.alterdata.pack.shared.CargoDto;

@Service
public class CargoServiceImpl implements CargoService{

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/java/br/com/alterdata/pack/images/cargo";
    
    @Autowired
	private CargoRepository _repositorioCargo;


    @Override
    public List<CargoDto> obterTodos(Pageable pageable) {
        Page<Cargo> cargos = _repositorioCargo.findAll(pageable);
        return cargos.stream().map(cargo -> new ModelMapper().map(cargo, CargoDto.class))
                     .collect(Collectors.toList());    
	}

    @Override
	public Optional<CargoDto> obterPorId(Long id){
        Optional<Cargo> encontrado = _repositorioCargo.findByIdCargo(id);

        if (!encontrado.isPresent()){
            throw new NotFoundException("Cargo não encontrado pelo ID:" + id);
        }
        return Optional.of(new ModelMapper().map(encontrado.get(), CargoDto.class));
    }


    @Override
	public List<Cargo> obterPorNome(String nome){
		List<Cargo> cargo = _repositorioCargo.findByNomeContainingIgnoreCase(nome);

		if(cargo.size() == 0){
		throw new NotFoundException("Não existe cargo com o nome: " + nome);
		}
		return cargo;
	}


    @Override
    public Cargo adicionarCargo(CargoDto cargoDto, MultipartFile arquivo){

        UUID uuid = UUID.randomUUID();

        ModelMapper mapper = new ModelMapper();
  
        Cargo cargo = mapper.map(cargoDto, Cargo.class);

        verificarSeCargoExiste(cargo);

        String fileName = uuid + arquivo.getOriginalFilename();
		Path fileNamePath = Paths.get(uploadDirectory, fileName);

		try {
			Files.write(fileNamePath, arquivo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();;
		}

		cargo.setAvatarName(fileName);

        cargo.setIdCargo(null);

        Cargo novoCargo = _repositorioCargo.save(cargo);

        return novoCargo;
    } 


    @Override
    public Cargo atualizar(Long id, CargoDto cargoDto) {

        Optional<Cargo> encontrado = _repositorioCargo.findByIdCargo(id);

        ModelMapper mapper = new ModelMapper();

        if(!encontrado.isPresent()){
            throw new NotFoundException("Cargo não encontrado pelo ID:" + id);
        }

        Cargo cargo = mapper.map(cargoDto, Cargo.class);

        cargo.setIdCargo(id);
        cargo.setAvatarName(encontrado.get().getAvatarName());

        if(cargoDto.getNome() == "" || cargoDto.getNome() == null){
            throw new BadRequestException("Nome não pode ser nulo!");
        }
        
        return _repositorioCargo.save(cargo);
    }


    @Override
    public void deletar(Long id) {
        Optional<Cargo> cargo = _repositorioCargo.findByIdCargo(id);

        if(!cargo.isPresent()){
            throw new NotFoundException("Cargo não encontrado pelo ID:" + id);
        }
        for (Usuario usuario : cargo.get().getUsuarios()) {
            usuario.setCargo(null);
        }
        File destino = new File(uploadDirectory, cargo.get().getAvatarName());

		try {
			destino.delete();
	   } catch (Exception e) {
		   throw new RuntimeException("Erro ao deletar imagem", e);
	   }
        this._repositorioCargo.deleteById(id);
	}


    @Override
	public byte[] retornarAvatar(Long id) throws IOException {

		Optional<CargoDto> cargo = obterPorId(id);

		File imagemArquivo = new File(uploadDirectory + "/" + cargo.get().getAvatarName());
		
		if(!cargo.get().getAvatarName().equals(null) || ! cargo.get().getAvatarName().equals("")){
			return Files.readAllBytes(imagemArquivo.toPath());			
		}
		throw new NotFoundException("Imagem não encontrada no cargo com ID: " + cargo.get().getIdCargo());
	}


    public Cargo editarAvatar(Long id, MultipartFile arquivo){
		UUID uuid = UUID.randomUUID();

        Optional<Cargo> cargo = _repositorioCargo.findById(id);

        if (!cargo.isPresent()) {
            throw new NotFoundException("Não foi encontrado nenhuma equipe com o Id: " + id);
        }

		String fileName = uuid + arquivo.getOriginalFilename();
		Path fileNamePath = Paths.get(uploadDirectory, fileName);

		try {
			Files.write(fileNamePath, arquivo.getBytes());
		} catch (IOException e) {
			e.printStackTrace();;
		}
		
		File destino = new File(uploadDirectory, cargo.get().getAvatarName());

		try {
			destino.delete();
	   } catch (Exception e) {
		   throw new RuntimeException("Erro ao deletar imagem", e);
	   }

		cargo.get().setAvatarName(fileName);

		return _repositorioCargo.save(cargo.get());
	}
    
    
    private void verificarSeCargoExiste(Cargo cargo){
        Optional<Cargo> cargoExiste =_repositorioCargo.findByNome(cargo.getNome());

        if(cargoExiste.isPresent()){
           throw new BadRequestException("O nome do Cargo já existe amigo :("); 
        }
    }
    
}
