package br.com.alterdata.pack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Usuario> obterTodos() {
        return _servicoUsuario.obterTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> obterPorId(@PathVariable(value = "id") Long id) {
        return  _servicoUsuario.obterPorId(id);
    }

    @PostMapping
    public ResponseEntity<Usuario> adicionar(@RequestBody Usuario usuario) {
        return _servicoUsuario.adicionar(usuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable(value = "id") Long id, @RequestBody Usuario usuario) {
        return _servicoUsuario.atualizar(id, usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> deletar(@PathVariable(value = "id") Long id) {
        return _servicoUsuario.deletar(id);
    }

}
