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

@Entity
@Table(name = "cargo")
@SequenceGenerator(name = "generator_cargo", sequenceName = "sequence_cargo", initialValue = 1, allocationSize = 1)
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator_usuario")
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    private String icone;

    @OneToMany(mappedBy="cargo")
    private List<Usuario> usuarios;

    public Cargo() {
    }

    public Cargo(String nome, String icone) {
        this.nome = nome;
        this.icone = icone;
    }

    public Cargo(Long id, String nome, String icone) {
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
