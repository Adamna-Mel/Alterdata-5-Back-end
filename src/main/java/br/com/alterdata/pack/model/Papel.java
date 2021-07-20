package br.com.alterdata.pack.model;

import javax.persistence.Entity;

@Entity
public class Papel {
    
    private Long id;
    private String nome;
    private String icone;

    
    public Papel() {
    }

    public Papel(String nome, String icone) {
        this.nome = nome;
        this.icone = icone;
    }

    public Papel(Long id, String nome, String icone) {
        this.id = id;
        this.nome = nome;
        this.icone = icone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    

    
}
