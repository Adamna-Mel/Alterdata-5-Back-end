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

import br.com.alterdata.pack.model.Papel;
import br.com.alterdata.pack.service.PapelService;
import br.com.alterdata.pack.shared.PapelDto;

@RestController
@RequestMapping("/api/papeis")
public class PapelController {
    
    @Autowired
    PapelService _papelUsuario;

    @GetMapping
    public ResponseEntity<List<Papel>> obterTodos() {
        return new ResponseEntity<>(_papelUsuario.obterTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Papel>> obterPorId(@PathVariable(value = "id") Long id) {
        
        Optional<Papel> papel = _papelUsuario.obterPorId(id);

        return  new ResponseEntity<>(papel, HttpStatus.OK);
    }
   
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Papel>> obterPorNome(@PathVariable(value = "nome") String nome){
        return new ResponseEntity<>(_papelUsuario.obterPorNome(nome), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Papel> adicionar(@RequestBody Papel papel) {

        Papel novoCargo = _papelUsuario.adicionarCargo(papel);
        return new ResponseEntity<>(novoCargo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<Papel>> atualizar(@PathVariable(value = "id") Long id, @RequestBody PapelDto papel) {
        
        Optional<Papel> papelAtt= _papelUsuario.atualizar(id, papel);

        return new ResponseEntity<>(papelAtt, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _papelUsuario.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
