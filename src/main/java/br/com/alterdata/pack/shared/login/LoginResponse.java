package br.com.alterdata.pack.shared.login;

import br.com.alterdata.pack.model.Usuario;

public class LoginResponse {
    
    private Usuario usuario;

    public LoginResponse(Usuario usuario) {

        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setusuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
