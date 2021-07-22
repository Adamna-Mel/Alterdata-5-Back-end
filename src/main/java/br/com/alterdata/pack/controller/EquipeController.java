package br.com.alterdata.pack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alterdata.pack.model.Equipe;
import br.com.alterdata.pack.service.EquipeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@Api("API PACK - Sistema de Status e Papéis")
@RestController
@RequestMapping("/api/equipes")
public class EquipeController {

    @Autowired
    EquipeService _equipeUsuario;

    @ApiOperation(value = "Retorna todas as equipes cadastradas")
    @GetMapping
    public ResponseEntity<List<Equipe>> obterTodos() {
        return new ResponseEntity<>(_equipeUsuario.obterTodos(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Filtra as equipes cadastradas de acordo com o Id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Equipe>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<Equipe> equipe = _equipeUsuario.obterPorId(id);
        return  new ResponseEntity<>(equipe, HttpStatus.OK);
    }

    @ApiOperation(value = "Filtra as equipes cadastradas de acordo com o nome")
    @GetMapping("/nome/{nome}")
	public ResponseEntity<List<Equipe>> obterPorNome(@PathVariable ("nome") String nome) {
		return new ResponseEntity<>(_equipeUsuario.obterPorNome(nome), HttpStatus.OK);
	}

    @ApiOperation(value = "Cadastra uma nova Equipe")
    @PostMapping
    public ResponseEntity<Equipe> adicionar(@RequestBody Equipe equipe) {
        Equipe novaEquipe = _equipeUsuario.criarEquipe(equipe);
        return new ResponseEntity<>(novaEquipe, HttpStatus.CREATED );
    }

    @ApiOperation(value = "Atualiza as informações de uma equipe de acordo com o id")
    @PutMapping("/{id}")
    public ResponseEntity<Equipe> atualizar(@PathVariable(value = "id") Long id, @RequestBody Equipe equipe) {
        Equipe equipeAtualizado = _equipeUsuario.atualizar(id, equipe);
        return new ResponseEntity<>(equipeAtualizado, HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta uma equipe de acordo com o id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _equipeUsuario.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
   
}
