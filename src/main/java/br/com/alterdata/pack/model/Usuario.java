package br.com.alterdata.pack.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "usuario")
@SequenceGenerator(name = "generator_usuario", sequenceName = "sequence_usuario", initialValue = 1, allocationSize = 1)
public class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generator_usuario")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String login;

    @JsonIgnore
    @Column(nullable = false)
    private String senha;

    @Column(nullable = true)
    private String avatarName;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = true)
    private String status;

    @ManyToOne()
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;

    @ManyToOne()
    @JoinColumn(name = "equipe_id")
    private Equipe equipe;

    public Usuario() {}
    
    public Usuario(String email, String login, String senha, String avatarName, String nome, String status, Cargo cargo, Equipe equipe){  

        this.email = email;
        this.login = login;
        this.senha = senha;
        this.avatarName = avatarName;
        this.nome = nome;
        this.status = status;
        this.cargo = cargo;
        this.equipe = equipe;
    }

    public Usuario(Long id, String email, String login, String senha, String avatarName, String nome, String status, Cargo cargo, Equipe equipe) {
            
        this.id = id;
        this.email = email;
        this.login = login;
        this.senha = senha;
        this.avatarName = avatarName;
        this.nome = nome;
        this.status = status;
        this.cargo = cargo;
        this.equipe = equipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getAvatarName() {
        return avatarName;
    }

    public void setAvatarName(String avatarName) {
        this.avatarName = avatarName;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cargo getCargo() {
         return cargo;
     }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Equipe getEquipe() {
         return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
  
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    //@JsonIgnore
    @Override
    public String getPassword() {
        return senha;
    }

    //@JsonIgnore
    @Override
    public String getUsername() {
        return login;
    }

    //@JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //@JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //@JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //@JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}
