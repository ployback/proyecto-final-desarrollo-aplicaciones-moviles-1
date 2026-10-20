package com.example.lentesapi.service;

import com.example.lentesapi.entity.Lente;
import com.example.lentesapi.repository.LenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LenteService {

    private final LenteRepository lenteRepository;

    public LenteService(LenteRepository lenteRepository) {
        this.lenteRepository = lenteRepository;
    }

    public Lente crear(Lente lente) {
        return lenteRepository.save(lente);
    }

    public List<Lente> listarTodos() {
        return lenteRepository.findAll();
    }

    public Optional<Lente> buscarPorId(Long id) {
        return lenteRepository.findById(id);
    }

    public Lente actualizar(Long id, Lente lenteActualizado) {
        Lente lente = lenteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lente no encontrado con id: " + id));

        lente.setNombre(lenteActualizado.getNombre());
        lente.setMarca(lenteActualizado.getMarca());
        lente.setPrecio(lenteActualizado.getPrecio());
        lente.setStock(lenteActualizado.getStock());
        lente.setDescripcion(lenteActualizado.getDescripcion());

        return lenteRepository.save(lente);
    }

    public void eliminar(Long id) {
        if (!lenteRepository.existsById(id)) {
            throw new RuntimeException("Lente no encontrado con id: " + id);
        }
        lenteRepository.deleteById(id);
    }
}
