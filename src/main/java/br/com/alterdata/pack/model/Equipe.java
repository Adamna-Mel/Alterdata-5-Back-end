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
    private Long idEquipe;

    @Column(unique = true, nullable = false)
    private String nome;

    private String icone;

    @OneToMany(mappedBy="equipe")
    private List<Usuario> membros;

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

    public Equipe() {}

    public Equipe(Long idEquipe, String nome, String icone) {
        this.idEquipe = idEquipe;
        this.nome = nome;
        this.icone = icone;
    }

}
