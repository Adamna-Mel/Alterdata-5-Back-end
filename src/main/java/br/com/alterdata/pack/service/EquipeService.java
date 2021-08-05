package br.com.alterdata.pack.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import br.com.alterdata.pack.model.Equipe;
import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.shared.EquipeDto;

public interface EquipeService {

    List<EquipeDto> obterTodos(Pageable pageable);
    Optional<EquipeDto> obterPorId(Long id);
    List<Equipe> obterPorNome(String nome);
    List<Usuario> obterUsuariosPorLogin(Long idEquipe,String login);
    Equipe criarEquipe(EquipeDto equipe);
    Equipe atualizar(Long id, EquipeDto equipe);
    byte[] retornarAvatar(Long id) throws IOException;
    Equipe editarAvatar(Long id, MultipartFile arquivo);
    void deletar(Long id);

}
