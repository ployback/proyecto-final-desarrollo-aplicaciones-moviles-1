package com.example.lentesapi.controller;

import com.example.lentesapi.service.LenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DeleteLenteController {

    private final LenteService lenteService;

    public DeleteLenteController(LenteService lenteService) {
        this.lenteService = lenteService;
    }

    @DeleteMapping("/lentes/{id}")
    public ResponseEntity<Void> eliminarLente(@PathVariable Long id) {
        try {
            lenteService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
