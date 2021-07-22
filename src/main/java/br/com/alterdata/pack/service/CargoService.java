package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alterdata.pack.exception.BadRequestException;
import br.com.alterdata.pack.exception.NotFoundException;
import br.com.alterdata.pack.model.Cargo;
import br.com.alterdata.pack.repository.CargoRepository;
import br.com.alterdata.pack.shared.CargoDto;

@Service
public class CargoService {
    
    @Autowired
	private CargoRepository _repositorioCargo;

    public List<Cargo> obterTodos() {
	    return this. _repositorioCargo.findAll();
	}

	public Optional<Cargo> obterPorId(Long id){

        Optional<Cargo> encontrado = _repositorioCargo.findById(id);

        if(!encontrado.isPresent()){
            throw new NotFoundException("Cargo não encontrado pelo ID:" + id);
            }
            return encontrado;
  
    }

	public List<Cargo> obterPorNome(String nome){

		List<Cargo> cargo = _repositorioCargo.findByNomeContainingIgnoreCase(nome);

		if(cargo.size() == 0){
		throw new NotFoundException("Não existe cargo com o nome: " + nome);
		}

		return cargo;

	}

    public Cargo adicionarCargo(Cargo cargo){

        verificarSeCargoExiste(cargo);

        cargo.setId(null);

        Cargo novoCargo = _repositorioCargo.save(cargo);

        return novoCargo;

    } 

    public Cargo atualizar(Long id, CargoDto cargo) {

        ModelMapper mapper = new ModelMapper();

        Cargo cargoMapeado = mapper.map(cargo, Cargo.class);

        verificarSeCargoExiste(cargoMapeado);

        Optional<Cargo> cargoAtualizado = obterPorId(id);

        if(cargo.getNome() != null){
            cargoAtualizado.get().setNome(cargo.getNome());
        }
        if(cargo.getIcone() != null){
            cargoAtualizado.get().setIcone(cargo.getIcone());
        }

        return _repositorioCargo.save(cargoAtualizado.get());
    }
 
    public void deletar(Long id) {

		obterPorId(id);
	      
		this._repositorioCargo.deleteById(id);
	}

    public void verificarSeCargoExiste(Cargo cargo){

        Optional<Cargo> cargoExiste =_repositorioCargo.findByNome(cargo.getNome());

        if(cargoExiste.isPresent()){
           throw new BadRequestException("Cargo já existe amigo :("); 
        }
    }
    
}
