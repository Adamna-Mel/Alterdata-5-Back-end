package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alterdata.pack.exception.BadRequestException;
import br.com.alterdata.pack.exception.NotFoundException;
import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.repository.UsuarioRepository;


@Service
public class UsuarioService {

	 		@Autowired
	    private UsuarioRepository _repositorioUsuario;

	    public List<Usuario> obterTodos() {
	        return this. _repositorioUsuario.findAll();
	    }
	    
	    public Optional<Usuario> obterPorId(Long id){

	            var encontrado = _repositorioUsuario.findById(id);

							if(encontrado.isEmpty()){
								throw new NotFoundException("Usuário não pode ser encontrado pelo ID:" + id);
							}
	            return encontrado;
	  
	    }

		public Optional<Usuario> obterPorLogin(String login){

			Optional<Usuario> usuario = _repositorioUsuario.findByLogin(login);

			if(usuario.isEmpty()){
				throw new NotFoundException("Usuário não pode ser encontrado pelo Login: " + login);
			}

			return usuario;

		}

	    public Usuario adicionar(Usuario usuario) {

					usuario.setId(null);

					validarCampos(usuario);

	        var adicionado = this._repositorioUsuario.save(usuario);
	        return adicionado;
	    }

	    
	    public Usuario atualizar(Long id, Usuario usuario) {

							obterPorId(id);

							validarCampos(usuario);

	            usuario.setId(id);

	            var usuarioAtualizado = this._repositorioUsuario.save(usuario);

	            return usuarioAtualizado;

	    }

	    
	    public void deletar(Long id) {

				obterPorId(id);
	      
				this._repositorioUsuario.deleteById(id);

	    }

			public void validarCampos(Usuario usuario){
				if (usuario.getLogin() == null || usuario.getLogin().isEmpty())
								throw new BadRequestException("Login não pode ser nulo!");
	
							if (usuario.getSenha() == null || usuario.getSenha().isEmpty())
								throw new BadRequestException("Senha não pode ser nulo!");
						
							if (usuario.getNome() == null || usuario.getNome().isEmpty())
								throw new BadRequestException("Nome não pode ser nulo!");
	
						var usuarioProcurado = this._repositorioUsuario.findByLogin(usuario.getLogin());

						if (usuarioProcurado.isPresent()){
							throw new BadRequestException("Usuário já existe com o Login: " + usuario.getLogin());
						}
			}

}
