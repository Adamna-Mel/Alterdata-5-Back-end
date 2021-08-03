package br.com.alterdata.pack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alterdata.pack.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findById(Long Id);
    Optional<Usuario> findByLogin(String login);
    List<Usuario> findByLoginContaining(String login);
    Optional<Usuario> findByEmail(String email);
} 
