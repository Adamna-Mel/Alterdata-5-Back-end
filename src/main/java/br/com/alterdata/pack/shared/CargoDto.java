package br.com.alterdata.pack.shared;

import java.util.List;

import br.com.alterdata.pack.model.Usuario;

public class CargoDto {
    private Long idCargo;
    private String nome;
    private String icone;
    private String cor1;
    private String cor2;

    private List<Usuario> usuarios;

    public CargoDto() {}

    public CargoDto(String nome, String icone) {
        this.nome = nome;
        this.icone = icone;
    }
    
    public CargoDto(String nome, String icone, String cor1, String cor2, List<Usuario> usuarios) {
            
        this.nome = nome;
        this.icone = icone;
        this.cor1 = cor1;
        this.cor2 = cor2;
        this.usuarios = usuarios;
    }

    public Long getIdCargo(){
        return idCargo;
    }

    public void setIdCargo(Long id){
        this.idCargo = id;
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
