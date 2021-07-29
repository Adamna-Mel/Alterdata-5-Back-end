package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.alterdata.pack.exception.BadRequestException;
import br.com.alterdata.pack.exception.UnauthorizedException;
import br.com.alterdata.pack.exception.NotFoundException;
import br.com.alterdata.pack.model.Cargo;
import br.com.alterdata.pack.model.Equipe;
import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.repository.CargoRepository;
import br.com.alterdata.pack.repository.EquipeRepository;
import br.com.alterdata.pack.repository.UsuarioRepository;
import br.com.alterdata.pack.security.JWTService;
import br.com.alterdata.pack.shared.UsuarioDto;
import br.com.alterdata.pack.shared.login.LoginResponse;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	private static final String headerPrefix = "Bearer ";

	@Autowired
	private UsuarioRepository _repositorioUsuario;

	@Autowired
	private CargoRepository _cargoRepository;

	@Autowired
	private EquipeRepository _repositorioEquipe;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Page<Usuario> obterTodos(Pageable pageable) {
		return  this._repositorioUsuario.findAll(pageable);
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		Optional<Usuario> encontrado = _repositorioUsuario.findById(id);

		if (!encontrado.isPresent()) {
			throw new NotFoundException("Usuário não pode ser encontrado pelo ID:" + id);
		}
		return encontrado;
	}

	@Override
	public List<Usuario> obterPorLogin(String login) {
		List<Usuario> usuarios = _repositorioUsuario.findByLoginContaining(login.toLowerCase());

		if (usuarios.size() == 0) {
			throw new NotFoundException("Nenhum usuário encontrado pelo Login: " + login);
		}
		return usuarios;
	}

	@Override
	public Usuario adicionar(UsuarioDto usuario) {
		ModelMapper mapper = new ModelMapper();

		Usuario novoUsuario = mapper.map(usuario, Usuario.class);
		novoUsuario.setId(null);

		String senha = passwordEncoder.encode(novoUsuario.getSenha());
		novoUsuario.setSenha(senha);

		validarCampos(novoUsuario);
		Optional<Usuario> usuarioProcurado = this._repositorioUsuario.findByLogin(novoUsuario.getLogin());

		if (usuarioProcurado.isPresent()) {
			throw new BadRequestException("Usuário já existe com o Login: " + usuario.getLogin());
		}
		Usuario adicionado = this._repositorioUsuario.save(novoUsuario);
		adicionarCargo(1L, adicionado.getId());

		//adicionarEquipe(adicionado.getId(), 1L);

		return adicionado;
	}

	@Override
	public Usuario atualizar(Long id, UsuarioDto usuario) {
		Optional<Usuario> usuarioAntigo = obterPorId(id);

		if (usuario.getLogin().equals(usuarioAntigo.get().getLogin()) || usuario.getLogin().equals("")) {
		}
		ModelMapper mapper = new ModelMapper();

		Usuario usuarioAtualizado = mapper.map(usuario, Usuario.class);
		usuarioAtualizado.setEquipe(usuarioAntigo.get().getEquipe());

		usuarioAtualizado.setCargo(usuarioAntigo.get().getCargo());
		validarCampos(usuarioAtualizado);

		usuarioAtualizado.setId(id);
		Optional<Usuario> usuarioProcurado = this._repositorioUsuario.findByLogin(usuarioAtualizado.getLogin());

		if (usuarioProcurado.isPresent()) {

			if (!usuarioProcurado.get().getId().equals(id)) {
				throw new BadRequestException("Usuário já existe com o Login: " + usuarioAtualizado.getLogin());
			}
		}
		Usuario usuarioSalvo = this._repositorioUsuario.save(usuarioAtualizado);

		return usuarioSalvo;
	}

	@Override
	public void deletar(Long id) {
		Optional<Usuario> usuario = obterPorId(id);

		if (!usuario.isPresent()) {
			throw new NotFoundException("Não existe equipe com o id informado: " + id);
		}
		this._repositorioUsuario.deleteById(id);
	}

	@Override
	public Usuario editar(Long id, UsuarioDto usuario) {
		Optional<Usuario> usuarioExistente = obterPorId(id);

		if (usuario.getStatus() != null)
			usuarioExistente.get().setStatus(usuario.getStatus());

		if (usuario.getAvatar() != null)
			usuarioExistente.get().setAvatar(usuario.getAvatar());

		Usuario usuarioSalvo = this._repositorioUsuario.save(usuarioExistente.get());

		return usuarioSalvo;
	}

	@Override
	public Usuario adicionarCargo(Long idCargo, Long idUsuario) {
		Optional<Cargo> cargo = _cargoRepository.findById(idCargo);

		Optional<Usuario> usuario = obterPorId(idUsuario);

		if (cargo.isPresent()) {
			usuario.get().setCargo(cargo.get());

			return _repositorioUsuario.save(usuario.get());
		}
		throw new NotFoundException("Cargo não encontrado pelo ID: " + idCargo + " :(");
	}

	@Override
	public Usuario adicionarEquipe(Long idUsuario, Long idEquipe) {
		Optional<Equipe> equipe = _repositorioEquipe.findById(idEquipe);

		Optional<Usuario> usuario = obterPorId(idUsuario);

		if (equipe.isPresent()) {
			usuario.get().setEquipe(equipe.get());

			return _repositorioUsuario.save(usuario.get());
		}
		throw new NotFoundException("Equipe não encontrado pelo ID: " + idEquipe + " :(");
	}

	@Override
	public LoginResponse logar(String login, String senha) {

		try {
			Authentication autenticacao = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(login, senha, Collections.emptyList()));

			SecurityContextHolder.getContext().setAuthentication(autenticacao);
			String token = headerPrefix + jwtService.gerarToken(autenticacao);

			Optional<Usuario> usuario = _repositorioUsuario.findByLogin(login);
			return new LoginResponse(usuario.get(), token);

		} catch (Exception e) {
			throw new UnauthorizedException("Credenciais inválidas :(");
		}
	}

	private void validarCampos(Usuario usuario) {
		if (usuario.getLogin() == null || usuario.getLogin().equals(""))
			throw new BadRequestException("Login não pode ser nulo!");

		if (usuario.getSenha() == null || usuario.getSenha().equals(""))
			throw new BadRequestException("Senha não pode ser nulo!");

		if (usuario.getNome() == null || usuario.getNome().equals(""))
			throw new BadRequestException("Nome não pode ser nulo ou vazio :(");

	}
}
