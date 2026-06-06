package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.ReseniaRequestDTO;
import com.duoc.stimy.gamehub.model.Resenia;
import com.duoc.stimy.gamehub.service.ReseniaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenias")
public class ReseniaController {

    private static final Logger log = LoggerFactory.getLogger(ReseniaController.class);

    @Autowired
    private ReseniaService reseniaService;

    @PostMapping
    public ResponseEntity<Resenia> crearResenia(@Valid @RequestBody ReseniaRequestDTO dto) {
        log.info("Controller: Recibiendo petición POST para nueva reseña del juego ID: {}", dto.getVideojuegoId());
        Resenia reseniaCreada = reseniaService.crearResenia(dto);
        return new ResponseEntity<>(reseniaCreada, HttpStatus.CREATED);
    }

    @GetMapping("/videojuego/{videojuegoId}")
    public ResponseEntity<List<Resenia>> obtenerReseniasPorVideojuego(@PathVariable Long videojuegoId) {
        log.info("Controller: Recibiendo petición GET de reseñas para el juego ID: {}", videojuegoId);
        List<Resenia> reseñas = reseniaService.obtenerReseniasPorVideojuego(videojuegoId);
        return new ResponseEntity<>(reseñas, HttpStatus.OK);
    }
}