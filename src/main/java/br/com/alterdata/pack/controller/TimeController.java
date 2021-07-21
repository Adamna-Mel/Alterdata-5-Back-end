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

import br.com.alterdata.pack.model.Time;
import br.com.alterdata.pack.service.TimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@Api("API PACK - Sistema de Status e Papéis")
@RestController
@RequestMapping("/api/times")
public class TimeController {

    @Autowired
    TimeService _servicoTime;

    @ApiOperation(value = "Retorna todos os times cadastradas")
    @GetMapping
    public ResponseEntity<List<Time>> obterTodos() {
        return new ResponseEntity<>(_servicoTime.obterTodos(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Filtra os times cadastrados de acordo com o Id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Time>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<Time> time = _servicoTime.obterPorId(id);
        return  new ResponseEntity<>(time, HttpStatus.OK);
    }

    @ApiOperation(value = "Filtra os times cadastrados de acordo com o nome")
    @GetMapping("/nome/{nome}")
	public ResponseEntity<Optional<Time>> obterPorNome(@PathVariable ("nome") String nome) {
		return new ResponseEntity<>(_servicoTime.obterPorNome(nome), HttpStatus.OK);
	}

    @ApiOperation(value = "Cadastra um novo time")
    @PostMapping
    public ResponseEntity<Time> adicionar(@RequestBody Time time) {
        Time novoTime = _servicoTime.adicionar(time);
        return new ResponseEntity<>(novoTime, HttpStatus.CREATED );
    }

    @ApiOperation(value = "Atualiza as informações de um time de acordo com o id")
    @PutMapping("/{id}")
    public ResponseEntity<Time> atualizar(@PathVariable(value = "id") Long id, @RequestBody Time time) {
        return new ResponseEntity<>(_servicoTime.atualizar(id, time), HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta um time de acordo com o id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _servicoTime.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
   
}
