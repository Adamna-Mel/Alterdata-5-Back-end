package br.com.alterdata.pack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "time")
@SequenceGenerator(name = "generator_time", sequenceName = "sequence_time", initialValue = 1, allocationSize = 1)
public class Time {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator_time")
    private Long id;

    @Column(unique = true, nullable = false)
    private String nome;


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


    public Time() {}

    public Time(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }   
    
}
