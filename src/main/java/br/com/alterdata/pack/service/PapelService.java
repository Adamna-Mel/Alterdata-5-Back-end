package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alterdata.pack.exception.BadRequestException;
import br.com.alterdata.pack.exception.NotFoundException;
import br.com.alterdata.pack.model.Papel;
import br.com.alterdata.pack.repository.PapelRepository;
import br.com.alterdata.pack.shared.PapelDto;

@Service
public class PapelService {
    
    @Autowired
	private PapelRepository _repositorioPapel;

    public List<Papel> obterTodos() {
	    return this. _repositorioPapel.findAll();
	}

	public Optional<Papel> obterPorId(Long id){

        Optional<Papel> encontrado = _repositorioPapel.findById(id);

                        if(!encontrado.isPresent()){
                            throw new NotFoundException("Não existe papel encontrado pelo ID:" + id);
                        }
            return encontrado;
  
    }

	public List<Papel> obterPorNome(String nome){

		List<Papel> papel = _repositorioPapel.findByNomeContainingIgnoreCase(nome);

		if(papel.size() == 0){
		throw new NotFoundException("Não existe papel encontrado com o nome: " + nome);
		}

		return papel;

	}

    public Papel adicionarCargo(Papel papel){

        verificarSePapelExiste(papel);

        Papel novoPapel = _repositorioPapel.save(papel);

        return novoPapel;

    } 

    public Papel atualizar(Long id, PapelDto papel) {

        ModelMapper mapper = new ModelMapper();

        Papel papelMapeado = mapper.map(papel, Papel.class);

        verificarSePapelExiste(papelMapeado);

        Optional<Papel> papelAtualizado = obterPorId(id);

        if(papel.getNome() != null){
            papelAtualizado.get().setNome(papel.getNome());
        }
        if(papel.getIcone() != null){
            papelAtualizado.get().setIcone(papel.getIcone());
        }

        return _repositorioPapel.save(papelAtualizado.get());
    }
 
    public void deletar(Long id) {

		obterPorId(id);
	      
		this._repositorioPapel.deleteById(id);
	}

    public void verificarSePapelExiste(Papel papel){

        Optional<Papel> papelExiste =_repositorioPapel.findByNome(papel.getNome());

        if(papelExiste.isPresent()){
           throw new BadRequestException("Papel já existe amigo :("); 
        }
    }
    
}
