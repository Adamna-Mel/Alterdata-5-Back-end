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
@Table(name = "equipe")
@SequenceGenerator(name = "generator_equipe", sequenceName = "sequence_equipe", initialValue = 1, allocationSize = 1)
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator_equipe")
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @OneToMany(mappedBy="equipe")
    private List<Usuario> membros;

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

    public Equipe() {}

    public Equipe(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
