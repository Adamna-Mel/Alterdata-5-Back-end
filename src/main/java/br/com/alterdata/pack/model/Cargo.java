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
@Table(name = "cargo")
@SequenceGenerator(name = "generator_cargo", sequenceName = "sequence_cargo", initialValue = 1, allocationSize = 1)
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator_cargo")    
    private Long idCargo;

    @Column(unique = true, nullable = false)
    private String nome;

    private String icone;

    private String cor1;

    private String cor2;

    @JsonIgnore
    @OneToMany(mappedBy="cargo")
    private List<Usuario> usuarios;

    public Cargo() {}

    public Cargo(String nome, String icone) {
        
        this.nome = nome;
        this.icone = icone;
    }

    public Cargo(Long idCargo, String nome, String icone) {

        this.idCargo = idCargo;
        this.nome = nome;
        this.icone = icone;
    }

    public Cargo(Long idCargo, String nome, String icone, String cor1, String cor2, List<Usuario> usuarios) {

        this.idCargo = idCargo;
        this.nome = nome;
        this.icone = icone;
        this.cor1 = cor1;
        this.cor2 = cor2;
        this.usuarios = usuarios;
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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
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

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

}
