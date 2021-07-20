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


@Service
public class UsuarioService {
	 @Autowired
	    private UsuarioRepository _repositorioUsuario;

	    public List<Usuario> obterTodos() {
	        return this. _repositorioUsuario.findAll();
	    }
	    
	    
	    public ResponseEntity<Optional<Usuario>> obterPorId(@PathVariable(value = "id") Long id){

	        try {
	            var encontrado = _repositorioUsuario.findById(id);

	            return new ResponseEntity<Optional<Usuario>>(encontrado, HttpStatus.OK);

	        }catch (Exception e) {
	            System.out.println(e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND );
	        }
	    }

	    
	    public ResponseEntity<Usuario> adicionar(@RequestBody Usuario usuario) {

	        var adicionado = this._repositorioUsuario.save(usuario);

	        return new ResponseEntity<Usuario>(adicionado, HttpStatus.CREATED);
	    }

	    
	    public ResponseEntity<Usuario> atualizar(@PathVariable(value = "id") Long id, @RequestBody Usuario usuario) {

	        try {
	            usuario.setId(id);

	            var usuarioAtualizado = this._repositorioUsuario.save(usuario);

	            return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);

	        }catch (Exception e) {
	            System.out.println(e.getMessage());

	            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	        }
	    }

	    
	    public ResponseEntity<Optional<Usuario>> deletar(@PathVariable(value = "id") Long id) {

	        try {
	            this._repositorioUsuario.deleteById(id);

	            return new ResponseEntity<Optional<Usuario>>(HttpStatus.OK);

	        }catch (Exception e) {
	            System.out.println(e.getMessage());

	            return new ResponseEntity<Optional<Usuario>>(HttpStatus.NOT_FOUND);
	        }    
	    }

}
