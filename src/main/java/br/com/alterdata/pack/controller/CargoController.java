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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/cargos")
public class CargoController {

    @Autowired
    CargoService _cargoUsuario;

    //#region GET

    @ApiOperation(value = "Retorna todos os cargos cadastradas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de cargos encontrado com sucesso :)"),
        @ApiResponse(code = 204, message = "Não existe cargo cadastrado :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping
    public ResponseEntity<List<CargoDto>> obterTodos(@PageableDefault(page=0, size=4) Pageable pageable) {
        return ResponseEntity.ok(_cargoUsuario.obterTodos(pageable));
    }


    @ApiOperation(value = "Filtra os cargos cadastrados de acordo com o Id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo encontrado com sucesso :)"),
        @ApiResponse(code = 404, message = "Não existe cargo com esse Id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping("/{id}")
    public ResponseEntity<Optional<CargoDto>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<CargoDto> cargo = _cargoUsuario.obterPorId(id);
        return new ResponseEntity<>(cargo, HttpStatus.OK);
    }


    @ApiOperation(value = "Filtra as cargos cadastrados de acordo com o nome")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo encontrado com sucesso :)"),
        @ApiResponse(code = 404, message = "Não existe caargo com esse nome :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Cargo>> obterPorNome(@PathVariable(value = "nome") String nome) {
        return new ResponseEntity<>(_cargoUsuario.obterPorNome(nome), HttpStatus.OK);
    }


    @ApiOperation("Retorna o avatar do cargo")
     @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Avatar retornado com sucesso :)"),
        @ApiResponse(code = 404, message = "Não existe cargo com esse id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @GetMapping("/avatar/{id}")
    public ResponseEntity<byte[]> retornarAvatar(@PathVariable(value = "id") Long id) throws IOException{
        return new ResponseEntity<>(_cargoUsuario.retornarAvatar(id), HttpStatus.OK);
    }

    //#endregion
    //#region POST

    @ApiOperation(value = "Cadastra um novo cargo")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Cargo criado com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe cargo com esse id :("),
        @ApiResponse(code = 415, message = "Mídia não suportada, vá com calma jovem ;)"),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PostMapping
    public ResponseEntity<Cargo> adicionar(CargoDto cargo) {
        Cargo novoCargo = _cargoUsuario.adicionarCargo(cargo);
        return new ResponseEntity<>(novoCargo, HttpStatus.CREATED);
    }

    //#endregion
    //#region PUT

    @ApiOperation(value = "Atualiza as informações de um cargo de acordo com o id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo atualizado com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe cargo com esse Id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cargo> atualizar(@PathVariable(value = "id") Long id, @RequestBody CargoDto cargo) {
        Cargo cargoAtt = _cargoUsuario.atualizar(id, cargo);
        return new ResponseEntity<>(cargoAtt, HttpStatus.OK);
    }

    //#endregion
    //#region PATCH
    
    @ApiOperation(value = "Alterar avatar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Avatar atualizado com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe cargo com esse Id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 415, message = "Mídia não suportada, vá com calma jovem ;)"),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PatchMapping("alterar-avatar/{id}")
    public ResponseEntity<Cargo> editarAvatar(@PathVariable(value = "id") Long id, @RequestParam("img") MultipartFile arquivo) {
        Cargo novoAvatarCargo = _cargoUsuario.editarAvatar(id, arquivo);     
        return new ResponseEntity<>(novoAvatarCargo, HttpStatus.OK);
    }

    //#endregion
    //#region DELETE

    @ApiOperation(value = "Deleta um cargo de acordo com o id")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Cargo removido com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe cargo com esse Id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _cargoUsuario.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //#endregion
}
