package br.com.alterdata.pack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alterdata.pack.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long>{

    Optional<Papel> findById(Long id);

    List<Papel> findByNomeContainingIgnoreCase(String nome);
}
