package com.example.lentesapi.controller;

import com.example.lentesapi.entity.Lente;
import com.example.lentesapi.service.LenteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GetAllLentesController {

    private final LenteService lenteService;

    public GetAllLentesController(LenteService lenteService) {
        this.lenteService = lenteService;
    }

    @GetMapping("/lentes")
    public ResponseEntity<List<Lente>> listarLentes() {
        return ResponseEntity.ok(lenteService.listarTodos());
    }
}
