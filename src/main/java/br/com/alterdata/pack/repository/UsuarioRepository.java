package br.com.alterdata.pack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alterdata.pack.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findById(Long Id);

    Optional<Usuario> findByLogin(String login);
}
