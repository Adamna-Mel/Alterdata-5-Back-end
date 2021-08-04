package br.com.alterdata.pack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alterdata.pack.model.EmailModel;

public interface EmailRepository extends JpaRepository<EmailModel, Long> {
        
}
