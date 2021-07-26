package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;

import br.com.alterdata.pack.model.Equipe;
import br.com.alterdata.pack.shared.EquipeDto;

public interface EquipeService {

    List<EquipeDto> obterTodos();
    Optional<Equipe> obterPorId(Long id);
    List<Equipe> obterPorNome(String nome);
    Equipe criarEquipe(Equipe equipe);
    Equipe atualizar(Long id, Equipe equipe);
    void deletar(Long id);
}
