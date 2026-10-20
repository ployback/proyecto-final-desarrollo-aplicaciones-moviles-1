package com.example.lentesapi.controller;

import com.example.lentesapi.entity.Lente;
import com.example.lentesapi.service.LenteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CreateLenteController {

    private final LenteService lenteService;

    public CreateLenteController(LenteService lenteService) {
        this.lenteService = lenteService;
    }

    @PostMapping("/lentes")
    public ResponseEntity<Lente> crearLente(@RequestBody Lente lente) {
        Lente creado = lenteService.crear(lente);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
