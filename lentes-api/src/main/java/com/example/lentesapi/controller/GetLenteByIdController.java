package com.example.lentesapi.controller;

import com.example.lentesapi.entity.Lente;
import com.example.lentesapi.service.LenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GetLenteByIdController {

    private final LenteService lenteService;

    public GetLenteByIdController(LenteService lenteService) {
        this.lenteService = lenteService;
    }

    @GetMapping("/lentes/{id}")
    public ResponseEntity<Lente> obtenerLentePorId(@PathVariable Long id) {
        return lenteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
