package com.example.lentesapi.controller;

import com.example.lentesapi.entity.Lente;
import com.example.lentesapi.service.LenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UpdateLenteController {

    private final LenteService lenteService;

    public UpdateLenteController(LenteService lenteService) {
        this.lenteService = lenteService;
    }

    @PutMapping("/lentes/{id}")
    public ResponseEntity<Lente> actualizarLente(@PathVariable Long id, @RequestBody Lente lente) {
        try {
            return ResponseEntity.ok(lenteService.actualizar(id, lente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
