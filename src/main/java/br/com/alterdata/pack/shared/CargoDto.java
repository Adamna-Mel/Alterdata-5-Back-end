package br.com.alterdata.pack.shared;

import java.util.List;

import br.com.alterdata.pack.model.Usuario;

public class CargoDto {
    private Long idCargo;
    private String nome;
    private String avatarName;
    private List<Usuario> usuarios;

    public CargoDto() {}

    public CargoDto(String nome, String avatarName) {
        this.nome = nome;
        this.avatarName = avatarName;
    }
    
    public CargoDto(String nome, String avatarName, String cor1, List<Usuario> usuarios) {
            
        this.nome = nome;
        this.avatarName = avatarName;
        this.usuarios = usuarios;
    }

    public Long getIdCargo(){
        return idCargo;
    }

    public void setIdCargo(Long id){
        this.idCargo = id;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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
    
}
