package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.shared.UsuarioDto;
import br.com.alterdata.pack.shared.login.LoginResponse;

public interface UsuarioService {

	Page<Usuario> obterTodos(Pageable pageable); 
	Optional<Usuario> obterPorId(Long id);
	List<Usuario> obterPorLogin(String login);
	Usuario adicionar(UsuarioDto usuario);
	Usuario atualizar(Long id, UsuarioDto usuario);
	void deletar(Long id);
	Usuario editar(Long id, UsuarioDto usuario);
	Usuario adicionarCargo(Long idCargo, Long idUsuario);
	Usuario adicionarEquipe(Long idUsuario, Long idEquipe);
	LoginResponse logar(String login, String senha);

}
