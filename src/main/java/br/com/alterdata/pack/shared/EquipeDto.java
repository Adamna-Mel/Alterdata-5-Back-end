package br.com.alterdata.pack.shared;

import java.util.List;

import br.com.alterdata.pack.model.Usuario;

public class EquipeDto {
    private Long idEquipe;
    private String nome;
    private String icone;
    private String cor1;
    private String cor2;

    private List<Usuario> membros;

    public EquipeDto() {}

    public EquipeDto(String nome) {
        this.nome = nome;
    }

    public EquipeDto(
        String nome, 
        String icone, 
        String cor1, 
        String cor2, 
        List<Usuario> membros) {
        
        this.nome = nome;
        this.icone = icone;
        this.cor1 = cor1;
        this.cor2 = cor2;
        this.membros = membros;
    }

    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long id) {
        this.idEquipe = id;
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

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

}
