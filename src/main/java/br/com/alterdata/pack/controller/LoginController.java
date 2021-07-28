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

@CrossOrigin(origins = "*")
@Api
@RestController
@RequestMapping("/api/login")
public class LoginController {
    
    @Autowired
    private UsuarioService _usuarioService;

    @ApiOperation("Fazer Login")
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) throws Exception {
        return new ResponseEntity<>(_usuarioService.logar(request.getLogin(), request.getSenha()), HttpStatus.OK);
    }
}
