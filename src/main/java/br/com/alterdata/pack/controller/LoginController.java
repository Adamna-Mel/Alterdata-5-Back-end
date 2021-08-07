package br.com.alterdata.pack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alterdata.pack.service.UsuarioService;
import br.com.alterdata.pack.shared.login.LoginRequest;
import br.com.alterdata.pack.shared.login.LoginResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*")
@Api
@RestController
@RequestMapping("/api/login")
public class LoginController {
    
    @Autowired
    private UsuarioService _usuarioService;

    @ApiOperation("Fazer Login")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Logado com sucesso :)"),
        @ApiResponse(code = 400, message = "Informação invalida :o"),
        @ApiResponse(code = 401, message = "Credenciais invalidas :o"),
        @ApiResponse(code = 404, message = "Não existe usuario com esse login :("),
        @ApiResponse(code = 403, message=  "Você não tem permissão para isso meu consagrado :("),
        @ApiResponse(code = 500, message = "Vish quinhetão, da uma olhadinha no código ;-;") 
    })
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws Exception {
        return new ResponseEntity<>(_usuarioService.logar(request.getLogin(), request.getSenha()), HttpStatus.OK);
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
        _usuarioService.enviarEmailEsqueciSenha(email);
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
