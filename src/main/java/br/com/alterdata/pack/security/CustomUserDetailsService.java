package br.com.alterdata.pack.security;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository _repositorioUsuario;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = getUser(() -> _repositorioUsuario.findByLogin(login));
		return usuario;
	}

	public UserDetails loadUserById(Long id) {
		Usuario usuario = getUser(() -> _repositorioUsuario.findById(id));
		return usuario;
	}

	private Usuario getUser(Supplier<Optional<Usuario>> supplier) {
		return supplier.get().orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
	}
}
