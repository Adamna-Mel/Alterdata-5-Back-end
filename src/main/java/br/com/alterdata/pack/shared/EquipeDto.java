package br.com.alterdata.pack.shared;

import java.util.List;

import br.com.alterdata.pack.model.Usuario;

public class EquipeDto {

    private Long id;
    private String nome;    
    private List<Usuario> membros;

    public EquipeDto() {}

    public EquipeDto(String nome) {
        this.nome = nome;
    }

    public EquipeDto(String nome, List<Usuario> membros) {
        
        this.nome = nome;
        this.membros = membros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
