package br.com.alterdata.pack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.service.UsuarioService;
import br.com.alterdata.pack.shared.UsuarioDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@Api("API PACK - Sistema de Status e Cargos")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService _servicoUsuario;

    @ApiOperation(value = "Retorna todos os usuários cadastradas")
    @GetMapping
    public ResponseEntity<List<Usuario>> obterTodos() {
        return new ResponseEntity<>(_servicoUsuario.obterTodos(), HttpStatus.OK);
    }

    @ApiOperation(value = "Filtra os usuários cadastrados de acordo com o Id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<Usuario> usuario = _servicoUsuario.obterPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @ApiOperation(value = "Filtra os usuários cadastrados de acordo com o login")
    @GetMapping("/login/{login}")
    public ResponseEntity<List<Usuario>> obterPorLogin(@PathVariable(value = "login") String login) {
        return new ResponseEntity<>(_servicoUsuario.obterPorLogin(login), HttpStatus.OK);
    }

    @ApiOperation(value = "Cadastra um novo usuário")
    @PostMapping
    public ResponseEntity<Usuario> adicionar(@RequestBody UsuarioDto usuario) {
        Usuario novoUsuario = _servicoUsuario.adicionar(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza as informações de um usuário de acordo com o id")
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable(value = "id") Long id, @RequestBody UsuarioDto usuario) {
        return new ResponseEntity<>(_servicoUsuario.atualizar(id, usuario), HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta um usuário de acordo com o id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _servicoUsuario.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Atualiza status de usuário de acordo com o id")
    @PatchMapping("{id}")
    public ResponseEntity<Usuario> editar(@PathVariable(value = "id") Long id,
            @RequestBody UsuarioDto usuario) {
        Usuario usuarioNovoStatus = _servicoUsuario.editar(id, usuario);
        return new ResponseEntity<>(usuarioNovoStatus, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona um cargo no usuario")
    @PatchMapping("{idUsuario}/cargo/{idCargo}")
    public ResponseEntity<Usuario> adicionarPapel(@PathVariable(value = "idCargo") Long idCargo,
            @PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario usuarioNovoStatus = _servicoUsuario.adicionarPapel(idCargo, idUsuario);
        return new ResponseEntity<>(usuarioNovoStatus, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona um equipe no usuario")
    @PatchMapping("{idUsuario}/equipe/{idEquipe}")
    public ResponseEntity<Usuario> adicionarEquipe(@PathVariable(value = "idEquipe") Long idEquipe,
            @PathVariable(value = "idUsuario") Long idUsuario) {
        Usuario usuarioNovoStatus = _servicoUsuario.adicionarEquipe(idUsuario, idEquipe);
        return new ResponseEntity<>(usuarioNovoStatus, HttpStatus.OK);
    }

}
