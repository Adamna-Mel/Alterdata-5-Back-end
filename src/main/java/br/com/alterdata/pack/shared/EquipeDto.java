package br.com.alterdata.pack.shared;

import java.util.List;

import br.com.alterdata.pack.model.Usuario;

public class EquipeDto {

    private Long id;
    private String nome;    
    private String avatarName;
    private List<Usuario> membros;

    public EquipeDto(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
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

}
