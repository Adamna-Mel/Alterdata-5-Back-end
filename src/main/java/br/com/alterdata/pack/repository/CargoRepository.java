package br.com.alterdata.pack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alterdata.pack.model.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long>{

    Optional<Cargo> findById(Long id);

    Optional<Cargo> findByNome(String nome);

    List<Cargo> findByNomeContainingIgnoreCase(String nome);
}
