package br.com.alterdata.pack.shared;


public class UsuarioDto {

    private String login;
    private String senha;
    private String avatar;
    private String nome;
    private String status;
   
    public UsuarioDto() {}

    public UsuarioDto(String login, String senha, String avatar, String nome, String status) {
        this.login = login;
        this.senha = senha;
        this.avatar = avatar;
        this.nome = nome;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
