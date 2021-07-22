package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alterdata.pack.exception.BadRequestException;
import br.com.alterdata.pack.exception.NotFoundException;
import br.com.alterdata.pack.model.Equipe;
import br.com.alterdata.pack.repository.EquipeRepository;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository _repositorioEquipe;

    public List<Equipe> obterTodos() {
        return this._repositorioEquipe.findAll();
    }

    public Optional<Equipe> obterPorId(Long id) {
        Optional<Equipe> encontrado = _repositorioEquipe.findById(id);

        if (!encontrado.isPresent()) {
            throw new NotFoundException("Não foi encontrado equipe com o ID: " + id);
        }
        return encontrado;
    }

    public List<Equipe> obterPorNome(String nome) {
        List<Equipe> encontrado = _repositorioEquipe.findByNomeContainingIgnoreCase(nome);

        if (encontrado.size() == 0) {
            throw new NotFoundException("Não foi encontrado equipe com o nome: " + nome);
        }
        return encontrado;
    }

    public Equipe criarEquipe(Equipe equipe) {
        equipe.setId(null);

        verificarSeEquipeExiste(equipe);

        if (equipe.getNome() == "" || equipe.getNome() == null) {
            throw new BadRequestException("Nome não pode ser nulo!");
        }
        Equipe novoEquipe = _repositorioEquipe.save(equipe);
        return novoEquipe;
    }

    public Equipe atualizar(Long id, Equipe equipe) {
        equipe.setId(id);

        if (equipe.getNome() == "" || equipe.getNome() == null) {
            throw new BadRequestException("Nome não pode ser nulo!");
        }
        Equipe equipeAtualizado = _repositorioEquipe.save(equipe);
        return equipeAtualizado;
    }

    public void deletar(Long id) {
        Optional<Equipe> existe = _repositorioEquipe.findById(id);

        if (!existe.isPresent()) {
            throw new NotFoundException("Não existe equipe com o id informado: " + id);
        }
        this._repositorioEquipe.deleteById(id);
    }

    public void verificarSeEquipeExiste(Equipe equipe) {
        Optional<Equipe> equipeExiste = _repositorioEquipe.findByNome(equipe.getNome());

        if (equipeExiste.isPresent()) {
            throw new BadRequestException("Opa! Já existe equipe com esse nome.");
        }
    }
}
