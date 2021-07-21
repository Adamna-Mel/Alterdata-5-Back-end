package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alterdata.pack.exception.BadRequestException;
import br.com.alterdata.pack.exception.NotFoundException;
import br.com.alterdata.pack.model.Papel;
import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.repository.PapelRepository;
import br.com.alterdata.pack.repository.UsuarioRepository;
import br.com.alterdata.pack.shared.UsuarioDto;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository _repositorioUsuario;

	@Autowired
	private PapelRepository _papelRepository;

	public List<Usuario> obterTodos() {
	    return this. _repositorioUsuario.findAll();
	    }
	    
	    public Optional<Usuario> obterPorId(Long id){
			Optional<Usuario> encontrado = _repositorioUsuario.findById(id);

			if(!encontrado.isPresent()){
				throw new NotFoundException("Usuário não pode ser encontrado pelo ID:" + id);
				}
	            return encontrado;
	    }


		public List<Usuario> obterPorLogin(String login){

			List<Usuario> usuarios = _repositorioUsuario.findByLoginContaining(login.toLowerCase());


			if(usuarios.size() == 0){
				throw new NotFoundException("Nenhum Usuário não pode ser encontrado pelo Login: " + login);
			}

			return usuarios;

	}

	public Usuario adicionar(UsuarioDto usuario) {

		ModelMapper mapper = new ModelMapper();

		Usuario novoUsuario = mapper.map(usuario, Usuario.class);

		novoUsuario.setId(null);

		validarCampos(novoUsuario);

	    Usuario adicionado = this._repositorioUsuario.save(novoUsuario);

		return adicionado;
	}

	    
	public Usuario atualizar(Long id, UsuarioDto usuario) {

		obterPorId(id);

		ModelMapper mapper = new ModelMapper();

		Usuario usuarioAtualizado = mapper.map(usuario, Usuario.class);

		validarCampos(usuarioAtualizado);

	    usuarioAtualizado.setId(id);

	    Usuario usuarioSalvo = this._repositorioUsuario.save(usuarioAtualizado);

	    return usuarioSalvo;

	}

	public void deletar(Long id) {

		obterPorId(id);
	      
		this._repositorioUsuario.deleteById(id);
	}

	public Optional<Usuario> editar(Long id, UsuarioDto usuario){

		Optional<Usuario> usuarioExistente = obterPorId(id);
	
		if (usuario.getStatus() != null)
		usuarioExistente.get().setStatus(usuario.getStatus());

		if (usuario.getAvatar() != null)
		usuarioExistente.get().setAvatar(usuario.getAvatar());
		
		return usuarioExistente;
	}

	public Usuario adicionarPapel(Long idPapel, Long idUsuario){

		Optional<Papel> papel = _papelRepository.findById(idPapel);

		Optional<Usuario> usuario = obterPorId(idUsuario);

		if(papel.isPresent()){
			usuario.get().setPapel(papel.get());

			return _repositorioUsuario.save(usuario.get());			
		}
		
		throw new NotFoundException("Papel não encontrado pelo ID: " + idPapel + " :(");

	}

	private void validarCampos(Usuario usuario){
		if (usuario.getLogin() == null)
			throw new BadRequestException("Login não pode ser nulo!");
		
		if (usuario.getSenha() == null)
			throw new BadRequestException("Senha não pode ser nulo!");

		if (usuario.getNome() == null)
			throw new BadRequestException("Nome não pode ser nulo!");

		Optional<Usuario> usuarioProcurado = this._repositorioUsuario.findByLogin(usuario.getLogin());

		if (usuarioProcurado.isPresent()){
			throw new BadRequestException("Usuário já existe com o Login: " + usuario.getLogin());
		}
	}
	
}
