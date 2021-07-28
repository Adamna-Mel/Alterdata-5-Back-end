package br.com.alterdata.pack.shared;


public class UsuarioDto {

    private String email;
    private String login;
    private String senha;
    private String avatarName;
    private String nome;
    private String status;

    // public UsuarioDto() {}

    // public UsuarioDto(String email,String login, String senha, String avatarName, String nome, String status) {

    //     this.email = email;   
    //     this.login = login;
    //     this.senha = senha;
    //     this.avatarName = avatarName;
    //     this.nome = nome;
    //     this.status = status;
    // }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
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
