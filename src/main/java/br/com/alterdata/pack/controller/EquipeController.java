package br.com.alterdata.pack.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.alterdata.pack.model.Equipe;
import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.service.EquipeService;
import br.com.alterdata.pack.shared.EquipeDto;
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
    public ResponseEntity<List<EquipeDto>> obterTodos(@PageableDefault(page=0, size=4) Pageable pageable) {
        return ResponseEntity.ok(_equipeUsuario.obterTodos(pageable));
    }
    
    @ApiOperation(value = "Filtra as equipes cadastradas de acordo com o Id")
    @GetMapping("/{id}")
    public ResponseEntity<Optional<EquipeDto>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<EquipeDto> equipe = _equipeUsuario.obterPorId(id);
        return  new ResponseEntity<>(equipe, HttpStatus.OK);
    }

    @ApiOperation(value = "Filtra as equipes cadastradas de acordo com o nome")
    @GetMapping("/nome/{nome}")
	public ResponseEntity<List<Equipe>> obterPorNome(@PathVariable ("nome") String nome) {
		return new ResponseEntity<>(_equipeUsuario.obterPorNome(nome), HttpStatus.OK);
	}

    @ApiOperation(value = "Filtra os usuários da equipe por login")
    @GetMapping("/{id}/login/{login}")
    public ResponseEntity<List<Usuario>> obterUsuariosPorLogin(@PathVariable ("id") Long id, @PathVariable ("login") String login) {
        return new ResponseEntity<>(_equipeUsuario.obterUsuariosPorLogin(id,login), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Cadastra uma nova Equipe")
    @PostMapping
    public ResponseEntity<Equipe> adicionar(EquipeDto equipe, @RequestParam("img") MultipartFile arquivo) {
        Equipe novaEquipe = _equipeUsuario.criarEquipe(equipe, arquivo);
        return new ResponseEntity<>(novaEquipe, HttpStatus.CREATED );
    }

    @ApiOperation(value = "Atualiza as informações de uma equipe de acordo com o id")
    @PutMapping("/{id}")
    public ResponseEntity<Equipe> atualizar(@PathVariable(value = "id") Long id, @RequestBody EquipeDto equipe) {
        Equipe equipeAtualizado = _equipeUsuario.atualizar(id, equipe);
        return new ResponseEntity<>(equipeAtualizado, HttpStatus.OK);
    }

    @ApiOperation(value = "Deleta uma equipe de acordo com o id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _equipeUsuario.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "Alterar avatar")
    @PatchMapping("alterar-avatar/{id}")
    public ResponseEntity<Equipe> editarAvatar(@PathVariable(value = "id") Long id, @RequestParam("img") MultipartFile arquivo) {
        Equipe novoAvatarEquipe = _equipeUsuario.editarAvatar(id, arquivo);     
        return new ResponseEntity<>(novoAvatarEquipe, HttpStatus.OK);
    }

}
