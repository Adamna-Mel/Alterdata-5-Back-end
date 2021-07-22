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

import br.com.alterdata.pack.model.Cargo;
import br.com.alterdata.pack.service.CargoService;
import br.com.alterdata.pack.shared.CargoDto;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    CargoService _cargoUsuario;

    @GetMapping
    public ResponseEntity<List<Cargo>> obterTodos() {
        return new ResponseEntity<>(_cargoUsuario.obterTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Cargo>> obterPorId(@PathVariable(value = "id") Long id) {

        Optional<Cargo> cargo = _cargoUsuario.obterPorId(id);

        return new ResponseEntity<>(cargo, HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Cargo>> obterPorNome(@PathVariable(value = "nome") String nome) {
        return new ResponseEntity<>(_cargoUsuario.obterPorNome(nome), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cargo> adicionar(@RequestBody Cargo cargo) {

        Cargo novoCargo = _cargoUsuario.adicionarCargo(cargo);
        return new ResponseEntity<>(novoCargo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargo> atualizar(@PathVariable(value = "id") Long id, @RequestBody CargoDto cargo) {

        Cargo cargoAtt = _cargoUsuario.atualizar(id, cargo);

        return new ResponseEntity<>(cargoAtt, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _cargoUsuario.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
