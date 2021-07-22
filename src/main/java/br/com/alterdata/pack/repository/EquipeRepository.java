package br.com.alterdata.pack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alterdata.pack.model.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    Optional<Equipe> findById(Long id);

    Optional<Equipe> findByNome(String nome);

    List<Equipe> findByNomeContainingIgnoreCase(String nome);
}
