package br.com.alterdata.pack.shared;

import java.util.List;

import br.com.alterdata.pack.model.Usuario;

public class EquipeDto {
    private Long idEquipe;
    private String nome;
    private String avatarName;
<<<<<<< HEAD
    private String cor1;
    private String cor2;
=======
    
>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1

    private List<Usuario> membros;

    public EquipeDto() {}

    public EquipeDto(String nome) {
        this.nome = nome;
    }

<<<<<<< HEAD
    public EquipeDto(String nome, String avatarName, String cor1, String cor2, List<Usuario> membros) {
        
        this.nome = nome;
        this.avatarName = avatarName;
        this.cor1 = cor1;
        this.cor2 = cor2;
=======
    public EquipeDto(String nome, String avatarName, List<Usuario> membros) {
        
        this.nome = nome;
        this.avatarName = avatarName;
>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1
        this.membros = membros;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long id) {
        this.idEquipe = id;
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    public void setMembros(List<Usuario> membros) {
        this.membros = membros;
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
