package br.com.alterdata.pack.shared;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioDtoCadastro {

        @NotBlank(message = "Campo não informado!")
        @Email(message = "Email inválido!")
        private String email;

        @NotBlank(message = "Campo não informado!")
        private String login;

        @NotBlank(message = "Campo não informado!")
        private String senha;

        @NotBlank(message = "Campo não informado!")
        private String nome;
        
        @NotBlank(message = "Campo não informado!")
        private String status;
    
        
       public UsuarioDtoCadastro() {}

        // public UsuarioDtoCadastro(@NotBlank @Email String email, @NotBlank String login, @NotBlank String senha,
        //         @NotBlank String nome, @NotBlank String status) {
        //     this.email = email;
        //     this.login = login;
        //     this.senha = senha;
        //     this.nome = nome;
        //     this.status = status;
        // }

        public String getEmail() {
            return email;
        }
    
        public void setEmail(String email) {
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
    
    }
    

