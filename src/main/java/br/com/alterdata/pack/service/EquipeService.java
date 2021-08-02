package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import br.com.alterdata.pack.model.Equipe;
import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.shared.EquipeDto;

public interface EquipeService {

    List<EquipeDto> obterTodos(Pageable pageable);
    Optional<EquipeDto> obterPorId(Long id);
    List<Equipe> obterPorNome(String nome);
    Equipe criarEquipe(Equipe equipe);
    Equipe atualizar(Long id, Equipe equipe);
    void deletar(Long id);
    List<Usuario> obterUsuariosPorLogin(Long idEquipe,String login);
}
