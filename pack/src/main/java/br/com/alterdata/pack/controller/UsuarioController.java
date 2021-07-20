package br.com.alterdata.pack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.service.UsuarioService;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
    UsuarioService _servicoUsuario;

    @GetMapping
    public ResponseEntity<List<Usuario>> obterTodos() {
        return new ResponseEntity<>(_servicoUsuario.obterTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> obterPorId(@PathVariable(value = "id") Long id) {
        
        var usuario = _servicoUsuario.obterPorId(id);

        if(usuario.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return  new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/login/{login}")
    public ResponseEntity<Optional<Usuario>> obterPorLogin(@PathVariable(value = "login") String login){
        return new ResponseEntity<>(_servicoUsuario.obterPorLogin(login), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionar(@RequestBody Usuario usuario) {

        var novoUsuario = _servicoUsuario.adicionar(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable(value = "id") Long id, @RequestBody Usuario usuario) {
        return new ResponseEntity<>(_servicoUsuario.atualizar(id, usuario), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _servicoUsuario.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
