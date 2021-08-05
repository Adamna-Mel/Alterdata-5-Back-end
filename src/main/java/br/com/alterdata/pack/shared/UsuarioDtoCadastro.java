package br.com.alterdata.pack.shared;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioDtoCadastro {

        @NotBlank(message = "Campo não informado!")
        @Email(message = "Email inválido!")
        private String email;

        @NotBlank(message = "Campo não informado!")
        private String login;

        @NotBlank(message = "Campo não informado!")
        @Size(min = 6, max = 6, message = "Senha deve ter 6 caracteres!")
        private String senha;

        @NotBlank(message = "Campo não informado!")
        private String nome;
        
        private String status;
       

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
    

