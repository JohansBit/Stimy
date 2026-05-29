package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.ReseniaRequestDTO;
import com.duoc.stimy.gamehub.model.Resenia;
import com.duoc.stimy.gamehub.service.ReseniaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resenias")
public class ReseniaController {

    @Autowired
    private ReseniaService reseniaService;

    @PostMapping
    public ResponseEntity<Resenia> crearResenia(@Valid @RequestBody ReseniaRequestDTO dto) {
        Resenia reseniaCreada = reseniaService.crearResenia(dto);
        return new ResponseEntity<>(reseniaCreada, HttpStatus.CREATED);
    }

    @GetMapping("/videojuego/{videojuegoId}")
    public ResponseEntity<List<Resenia>> obtenerReseniasPorVideojuego(@PathVariable Long videojuegoId) {
        List<Resenia> reseñas = reseniaService.obtenerReseniasPorVideojuego(videojuegoId);
        return new ResponseEntity<>(reseñas, HttpStatus.OK);
    }
}