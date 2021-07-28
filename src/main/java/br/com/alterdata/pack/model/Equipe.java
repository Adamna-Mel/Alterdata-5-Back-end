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

    private String icone;

    @JsonIgnore
    @OneToMany(mappedBy="equipe")
    private List<Usuario> membros;

    private String cor1;

    private String cor2;

    public Equipe() {}

    public Equipe(Long idEquipe, String nome, String icone) {
        this.idEquipe = idEquipe;
        this.nome = nome;
        this.icone = icone;
    }

    public Equipe(Long idEquipe, String nome, String icone, List<Usuario> membros) {

        this.idEquipe = idEquipe;
        this.nome = nome;
        this.icone = icone;
        this.membros = membros;
    }

    public Equipe(Long idEquipe, String nome, String icone, List<Usuario> membros, String cor1, String cor2) {

        this.idEquipe = idEquipe;
        this.nome = nome;
        this.icone = icone;
        this.membros = membros;
        this.cor1 = cor1;
        this.cor2 = cor2;
    }

    public String getCor1() {
        return cor1;
    }

    public void setCor1(String cor1) {
        this.cor1 = cor1;
    }

    public String getCor2() {
        return cor2;
    }

    public void setCor2(String cor2) {
        this.cor2 = cor2;
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

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public List<Usuario> getMembros() {
        return membros;
    }

    public void setMembros(List<Usuario> membros) {
        this.membros = membros;
    }
}
