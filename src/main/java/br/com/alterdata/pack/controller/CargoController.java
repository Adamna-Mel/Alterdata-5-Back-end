package br.com.alterdata.pack.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.alterdata.pack.model.Cargo;
import br.com.alterdata.pack.service.CargoService;
import br.com.alterdata.pack.shared.CargoDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    CargoService _cargoUsuario;

    @ApiOperation(value = "Retorna todos os cargos cadastradas")
    @GetMapping
    public ResponseEntity<List<CargoDto>> obterTodos(@PageableDefault(page=0, size=4) Pageable pageable) {
        return ResponseEntity.ok(_cargoUsuario.obterTodos(pageable));
    }

    @ApiOperation(value = "Filtra os cargos cadastrados de acordo com o Id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CargoDto>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<CargoDto> cargo = _cargoUsuario.obterPorId(id);
        return new ResponseEntity<>(cargo, HttpStatus.OK);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Cargo>> obterPorNome(@PathVariable(value = "nome") String nome) {
        return new ResponseEntity<>(_cargoUsuario.obterPorNome(nome), HttpStatus.OK);
    }

    @ApiOperation("Retorna o avatar do cargo")
    @GetMapping("/avatar/{id}")
    public ResponseEntity<byte[]> retornarAvatar(@PathVariable(value = "id") Long id) throws IOException{

        return new ResponseEntity<>(_cargoUsuario.retornarAvatar(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Cadastra um novo cargo")
    @ApiParam
    @PostMapping
    public ResponseEntity<Cargo> adicionar(Cargo cargo, @RequestParam("img") MultipartFile arquivo) {
        Cargo novoCargo = _cargoUsuario.adicionarCargo(cargo, arquivo);
        return new ResponseEntity<>(novoCargo, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza as informações de um cargo de acordo com o id")
    @PutMapping("/{id}")
    public ResponseEntity<Cargo> atualizar(@PathVariable(value = "id") Long id, @RequestBody CargoDto cargo) {
        Cargo cargoAtt = _cargoUsuario.atualizar(id, cargo);
        return new ResponseEntity<>(cargoAtt, HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta um cargo de acordo com o id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _cargoUsuario.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Alterar avatar")
    @PatchMapping("alterar-avatar/{id}")
    public ResponseEntity<Cargo> editarAvatar(@PathVariable(value = "id") Long id, @RequestParam("img") MultipartFile arquivo) {
        Cargo novoAvatarCargo = _cargoUsuario.editarAvatar(id, arquivo);     
        return new ResponseEntity<>(novoAvatarCargo, HttpStatus.OK);
    }

}
