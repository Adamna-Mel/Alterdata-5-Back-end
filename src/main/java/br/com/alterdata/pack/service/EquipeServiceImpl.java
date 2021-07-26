package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alterdata.pack.exception.BadRequestException;
import br.com.alterdata.pack.exception.NotFoundException;
import br.com.alterdata.pack.model.Equipe;
import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.repository.EquipeRepository;

@Service
public class EquipeServiceImpl implements EquipeService{
    
    @Autowired
    private EquipeRepository _repositorioEquipe;

    @Override
    public List<Equipe> obterTodos() {
        return this._repositorioEquipe.findAll();
    }

    @Override
    public Optional<Equipe> obterPorId(Long id) {
        Optional<Equipe> encontrado = _repositorioEquipe.findByIdEquipe(id);

        if(!encontrado.isPresent()) {
            throw new NotFoundException("Não foi encontrado equipe com o ID: " + id);
        }
        return encontrado;
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
    public Equipe criarEquipe(Equipe equipe) {
        equipe.setIdEquipe(null);

        verificarSeEquipeExiste(equipe);

        if (equipe.getNome() == "" || equipe.getNome() == null) {
            throw new BadRequestException("Nome não pode ser nulo!");
        }
        Equipe novoEquipe = _repositorioEquipe.save(equipe);
        return novoEquipe;
    }
    
    @Override
    public Equipe atualizar(Long id, Equipe equipe) {
        equipe.setIdEquipe(id);

        if(equipe.getNome() == "" || equipe.getNome() == null){
            throw new BadRequestException("Nome não pode ser nulo!");
        }
        Equipe equipeAtualizado = _repositorioEquipe.save(equipe);
        return equipeAtualizado;
    }

    @Override
    public void deletar(Long id) {

        Optional<Equipe> equipe = obterPorId(id);

        if(equipe.isPresent()){
            if(equipe.get().getIdEquipe() == 1L){
                throw new BadRequestException("Essa equipe é default e não pode ser apagada :(");
            }
        }
        if (!equipe.isPresent()) {
            throw new NotFoundException("Não existe equipe com o id informado: " + id);
        }
        this._repositorioEquipe.deleteById(id);
    }

    

    private void verificarSeEquipeExiste(Equipe equipe) {
        Optional<Equipe> equipeExiste = _repositorioEquipe.findByNome(equipe.getNome());

        if (equipeExiste.isPresent()) {
            throw new BadRequestException("Opa! Já existe equipe com esse nome.");
        }
    }
}
