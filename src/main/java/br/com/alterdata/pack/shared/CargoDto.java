package br.com.alterdata.pack.shared;

import java.util.List;

import br.com.alterdata.pack.model.Usuario;

public class CargoDto {

    private Long idCargo;
    private String nome;
    private String avatarName;
    private List<Usuario> usuarios;

    public CargoDto() {      
    }

    public CargoDto(Long idCargo, String nome, String avatarName, List<Usuario> usuarios) {
        this.idCargo = idCargo;
        this.nome = nome;
        this.avatarName = avatarName;
        this.usuarios = usuarios;
    }

    public CargoDto(String nome, String avatarName, List<Usuario> usuarios) {
        this.nome = nome;
        this.avatarName = avatarName;
        this.usuarios = usuarios;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    

}
