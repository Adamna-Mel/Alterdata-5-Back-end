package br.com.alterdata.pack.service;

import java.util.List;
import java.util.Optional;

import br.com.alterdata.pack.model.Cargo;

import br.com.alterdata.pack.shared.CargoDto;

public interface CargoService {

    List<Cargo> obterTodos();
    Optional<Cargo> obterPorId(Long id);
    List<Cargo> obterPorNome(String nome);
    Cargo adicionarCargo(Cargo cargo);
    Cargo atualizar(Long id, CargoDto cargo);
    void deletar(Long id);
    void verificarSeCargoExiste(Cargo cargo);
    
}
