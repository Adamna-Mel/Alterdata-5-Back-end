package br.com.alterdata.pack.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import br.com.alterdata.pack.model.Cargo;

import br.com.alterdata.pack.shared.CargoDto;

public interface CargoService {

    List<CargoDto> obterTodos(Pageable pageable);
    Optional<CargoDto> obterPorId(Long id);
    List<Cargo> obterPorNome(String nome);
    byte[] retornarAvatar(Long id) throws IOException;
    Cargo adicionarCargo(CargoDto cargo);
    Cargo atualizar(Long id, CargoDto cargo);
    Cargo editarAvatar(Long id, MultipartFile arquivo) throws IOException; 
    void deletar(Long id); 
}
