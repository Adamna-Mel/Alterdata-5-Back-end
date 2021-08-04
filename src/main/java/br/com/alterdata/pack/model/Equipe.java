package br.com.alterdata.pack.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "equipe")
@SequenceGenerator(name = "generator_equipe", sequenceName = "sequence_equipe", initialValue = 1, allocationSize = 1)
public class Equipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator_equipe")
    private Long idEquipe;

    @Column(unique = true, nullable = false)
    private String nome;

    @Column(nullable = false)
    private String avatarName;

    @JsonIgnore
    @OneToMany(mappedBy="equipe")
    private List<Usuario> membros;

    
    public Equipe() {}

    public Equipe(Long idEquipe, String nome, String avatarName) {
        this.idEquipe = idEquipe;
        this.nome = nome;
        this.avatarName = avatarName;
    }

    public Equipe(Long idEquipe, String nome, String avatarName, List<Usuario> membros) {

        this.idEquipe = idEquipe;
        this.nome = nome;
        this.avatarName = avatarName;
        this.membros = membros;
    }

    public Equipe(Long idEquipe, String nome, String avatarName, List<Usuario> membros, String cor1, String cor2) {

        this.idEquipe = idEquipe;
        this.nome = nome;
        this.avatarName = avatarName;
        this.membros = membros;
    }


    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
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

    public List<Usuario> getMembros() {
        return membros;
    }

    public void setMembros(List<Usuario> membros) {
        this.membros = membros;
    }
}
