package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alterdata.pack.exception.BadRequestException;
import br.com.alterdata.pack.exception.NotFoundException;
import br.com.alterdata.pack.model.Cargo;
import br.com.alterdata.pack.repository.CargoRepository;
import br.com.alterdata.pack.shared.CargoDto;

@Service
public class CargoServiceImpl implements CargoService{
    
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
    public Cargo adicionarCargo(Cargo cargo){
        
        verificarSeCargoExiste(cargo);

        cargo.setIdCargo(null);

        if (cargo.getCor1() == null){
            cargo.setCor1("#000");
        }
        if (cargo.getCor2() == null){
            cargo.setCor2("#fff");
        }
        Cargo novoCargo = _repositorioCargo.save(cargo);

        return novoCargo;
    } 

    @Override
    public Cargo atualizar(Long id, CargoDto cargo) {
        Optional<Cargo> cargoAtual = _repositorioCargo.findByIdCargo(id);

        if(!cargoAtual.isPresent()){
            throw new NotFoundException("Cargo não encontrado pelo ID:" + id);
        }
        ModelMapper mapper = new ModelMapper();
        Cargo cargoMapeado = mapper.map(cargo, Cargo.class);

        verificarSeCargoExiste(cargoMapeado);

        if(cargo.getNome() != null){
            cargoAtual.get().setNome(cargo.getNome());
        }
        if(cargo.getIcone() != null){
            cargoAtual.get().setIcone(cargo.getIcone());
        }
        return _repositorioCargo.save(cargoAtual.get());
    }

    @Override
    public void deletar(Long id) {
        Optional<Cargo> cargo = _repositorioCargo.findByIdCargo(id);

        if(!cargo.isPresent()){
            throw new NotFoundException("Cargo não encontrado pelo ID:" + id);
        }
        if(cargo.isPresent()){
            if(cargo.get().getIdCargo() == 1L){
                throw new BadRequestException("Esse cargo é default e não pode ser apagado :(");
            }
        }
		this._repositorioCargo.deleteById(id);
	}

    private void verificarSeCargoExiste(Cargo cargo){
        Optional<Cargo> cargoExiste =_repositorioCargo.findByNome(cargo.getNome());

        if(cargoExiste.isPresent()){
           throw new BadRequestException("O nome do Cargo já existe amigo :("); 
        }
    }
    
}
