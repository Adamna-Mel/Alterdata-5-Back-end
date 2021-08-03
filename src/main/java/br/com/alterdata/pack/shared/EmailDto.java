package br.com.alterdata.pack.shared;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailDto {

    @NotBlank
<<<<<<< HEAD
    private String ownerRef;
    @NotBlank
=======
>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1
    @Email
    private String emailFrom;
    @NotBlank
    @Email
    private String emailTo;
    @NotBlank
    private String subject;
    @NotBlank
    private String text;

    public EmailDto() {}

<<<<<<< HEAD
    public EmailDto(@NotBlank String ownerRef, @NotBlank @Email String emailFrom, @NotBlank @Email String emailTo,
            @NotBlank String subject, @NotBlank String text) {
        this.ownerRef = ownerRef;
=======
    public EmailDto(String emailFrom, String emailTo, String subject, String text) {
>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.subject = subject;
        this.text = text;
    }

<<<<<<< HEAD
    public String getOwnerRef() {
        return ownerRef;
    }

    public void setOwnerRef(String ownerRef) {
        this.ownerRef = ownerRef;
    }
=======
>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1

    public String getEmailFrom() {
        return emailFrom;
    }

<<<<<<< HEAD
=======

>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1
    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

<<<<<<< HEAD
=======

>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1
    public String getEmailTo() {
        return emailTo;
    }

<<<<<<< HEAD
=======

>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1
    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }

<<<<<<< HEAD
=======

>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1
    public String getSubject() {
        return subject;
    }

<<<<<<< HEAD
=======

>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1
    public void setSubject(String subject) {
        this.subject = subject;
    }

<<<<<<< HEAD
=======

>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1
    public String getText() {
        return text;
    }

<<<<<<< HEAD
    public void setText(String text) {
        this.text = text;
    }  
    
}
=======

    public void setText(String text) {
        this.text = text;
    }    
    
}
>>>>>>> f7feb2cf845817fcc45a1b46b84bc6cc6c134fb1
