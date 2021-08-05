package br.com.alterdata.pack.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import br.com.alterdata.pack.model.Usuario;
import br.com.alterdata.pack.service.UsuarioService;
import br.com.alterdata.pack.shared.UsuarioDto;
import br.com.alterdata.pack.shared.UsuarioDtoCadastro;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@Api("API PACK - Sistema de Status e Cargos")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService _servicoUsuario;
    
    @ApiOperation(value = "Retorna todos os usuários cadastrados")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Lista de usuarios encontrada com sucesso :)"),
        @ApiResponse(code = 204, message = "Não existe usuario cadastrado :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @GetMapping(produces="application/json")
    public ResponseEntity<Page<Usuario>> obterTodos(@PageableDefault(page=0, size=4) Pageable pageable) {
        if(_servicoUsuario.obterTodos(pageable).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(_servicoUsuario.obterTodos(pageable), HttpStatus.OK);
    }


    @ApiOperation(value = "Filtra os usuários cadastrados de acordo com o Id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuario encontrado com sucesso :)"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse Id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> obterPorId(@PathVariable(value = "id") Long id) {
        Optional<Usuario> usuario = _servicoUsuario.obterPorId(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }


    @ApiOperation(value = "Filtra os usuários cadastrados de acordo com o login")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuario encontrado com sucesso :)"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse login :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @GetMapping("/login/{login}")
    public ResponseEntity<List<Usuario>> obterPorLogin(@PathVariable(value = "login") String login) {
        return new ResponseEntity<>(_servicoUsuario.obterPorLogin(login), HttpStatus.OK);
    }

    
    @ApiOperation("Retorna o avatar do usuario")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Avatar retornado com sucesso :)"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse login :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @GetMapping("/avatar/{id}")
    public ResponseEntity<byte[]> retornarAvatar(@PathVariable(value = "id") Long id) throws IOException{
        return new ResponseEntity<>(_servicoUsuario.retornarAvatar(id), HttpStatus.OK);
    }


    @ApiOperation(value = "Cadastra um novo usuário")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Usuario criado com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse login :("),
        @ApiResponse(code = 415, message = "Mídia não suportada, vá com calma jovem ;)"),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @PostMapping
    public ResponseEntity<Usuario> adicionar(@Valid UsuarioDtoCadastro usuario) {
        Usuario novoUsuario = _servicoUsuario.adicionar(usuario);   
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }
    

    @ApiOperation(value = "Envia um email com nova senha")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Email enviado com sucesso com sua nova senha :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse email :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PostMapping("/esqueci-senha")
    public ResponseEntity<Void> enviarEmailEsqueciSenha(String email) {
        _servicoUsuario.enviarEmailEsqueciSenha(email);
        return new ResponseEntity<>( HttpStatus.OK);
    }


    @ApiOperation(value = "Atualiza as informações de um usuário de acordo com o id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Usuario atualizado com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse Id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable(value = "id") Long id, @RequestBody UsuarioDto usuario) {
        return new ResponseEntity<>(_servicoUsuario.atualizar(id, usuario), HttpStatus.OK);
    }


    @ApiOperation(value = "Atualiza status de usuário de acordo com o id")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Status do usuario atualizado com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse Id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @PatchMapping("status/{id}")
    public ResponseEntity<Usuario> editarStatus(@PathVariable(value = "id") Long id, @RequestBody UsuarioDto usuario) {
        Usuario usuarioNovoStatus = _servicoUsuario.editarStatus(id, usuario);    
        return new ResponseEntity<>(usuarioNovoStatus, HttpStatus.OK);
    }


    @ApiOperation(value = "Alterar avatar")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Avatar do usuario atualizado com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse Id :("),
        @ApiResponse(code = 415, message = "Mídia não suportada, vá com calma jovem ;)"),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @PatchMapping("alterar-avatar/{id}")
    public ResponseEntity<Usuario> editarAvatar(@PathVariable(value = "id") Long id, @RequestParam("img") MultipartFile arquivo) {
        Usuario usuarioNovoStatus = _servicoUsuario.editarAvatar(id, arquivo);      
        return new ResponseEntity<>(usuarioNovoStatus, HttpStatus.OK);
    }

    @ApiOperation(value = "Adiciona um cargo no usuario")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Cargo do usuario atualizado com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse Id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @PatchMapping("{idUsuario}/cargo/{idCargo}")
    public ResponseEntity<Usuario> adicionarCargo(@PathVariable(value = "idCargo") Long idCargo, @PathVariable(value = "idUsuario") Long idUsuario){
        Usuario usuarioNovoStatus = _servicoUsuario.adicionarCargo(idCargo, idUsuario);    
        return new ResponseEntity<>(usuarioNovoStatus, HttpStatus.OK);
    }


    @ApiOperation(value = "Adiciona uma equipe no usuario")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Equipe do usuario atualizado com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse Id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @PatchMapping("{idUsuario}/equipe/{idEquipe}")
    public ResponseEntity<Usuario> adicionarEquipe(@PathVariable(value = "idEquipe") Long idEquipe, @PathVariable(value = "idUsuario") Long idUsuario){
        Usuario usuarioNovoStatus = _servicoUsuario.adicionarEquipe(idUsuario, idEquipe);    
        return new ResponseEntity<>(usuarioNovoStatus, HttpStatus.OK);
    }

    @ApiOperation(value = "Alterar senha do usuario")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Senha alterada com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse Id :("),
        @ApiResponse(code = 401, message = "Senha invalida"),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PatchMapping("alterar-senha/{id}")
    public ResponseEntity<Void> alterarSenha(@PathVariable(value = "id") Long id, String antigaSenha, String novaSenha) {
        _servicoUsuario.alterarSenha(id, antigaSenha, novaSenha);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "Deleta um usuário de acordo com o id")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Usuario removido da equipe com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse Id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @DeleteMapping("sair-da-equipe/{id}")
    public ResponseEntity<Void> removerUsuarioDaEquipe(@PathVariable(value = "id") Long id) {
        _servicoUsuario.removerUsuarioDaEquipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @ApiOperation(value = "Deleta um usuário de acordo com o id")
    @ApiResponses(value = {
        @ApiResponse(code = 204, message = "Usuario deletado com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse Id :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable(value = "id") Long id) {
        _servicoUsuario.deletar(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
