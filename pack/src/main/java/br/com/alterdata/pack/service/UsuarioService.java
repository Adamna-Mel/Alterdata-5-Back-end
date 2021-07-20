package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.repository.UsuarioRepository;
import br.com.alterdata.pack.shared.UsuarioDto;


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
					Optional.empty();
				}
	            return encontrado;
	  
	    }

		public Optional<Usuario> obterPorLogin(String login){

			Optional<Usuario> usuario = _repositorioUsuario.findByLogin(login);

			if(usuario.isEmpty()){
				return Optional.empty();
			}

			return usuario;

		}

	    public Usuario adicionar(Usuario usuario) {

	        var adicionado = this._repositorioUsuario.save(usuario);

	        return adicionado;
	    }

	    
	    public Usuario atualizar(Long id, Usuario usuario) {

	        try {
	            usuario.setId(id);

	            var usuarioAtualizado = this._repositorioUsuario.save(usuario);

	            return usuarioAtualizado;

	        }catch (Exception e) {
	            System.out.println(e.getMessage());

	            return null;
	        }
	    }

	    public void deletar(Long id) {

	        try {
	            this._repositorioUsuario.deleteById(id);

	        
	        }catch (Exception e) {
	            System.out.println(e.getMessage());
	        }    
	    }

		public Optional<Usuario> editarStatus(Long id, UsuarioDto usuario){
			var usuarioExistente = _repositorioUsuario.findById(id);
			
			usuarioExistente.get().setStatus(usuario.getStatus());
			
			return usuarioExistente;
		}

		public Optional<Usuario> editarTime(Long id, UsuarioDto usuario){

			var usuarioExistente = _repositorioUsuario.findById(id);
			
			usuarioExistente.get().setTime(usuario.getTime());
			
			return usuarioExistente;
		}

		public Optional<Usuario> editarPapel(Long id, UsuarioDto usuario){

			var usuarioExistente = _repositorioUsuario.findById(id);
			
			usuarioExistente.get().setPapel(usuario.getPapel());
			
			return usuarioExistente;
		}

		public Optional<Usuario> editarAvatar(Long id, UsuarioDto usuario){

			var usuarioExistente = _repositorioUsuario.findById(id);
			
			usuarioExistente.get().setAvatar(usuario.getAvatar());
			
			return usuarioExistente;
		}

}
