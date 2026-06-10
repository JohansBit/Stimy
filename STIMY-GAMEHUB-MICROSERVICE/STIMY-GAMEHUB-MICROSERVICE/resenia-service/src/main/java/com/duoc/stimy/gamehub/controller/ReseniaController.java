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
    public ResponseEntity<Resenia> agregar(@Valid @RequestBody ReseniaRequestDTO dto) {
        log.info("[RESENIA-CONTROLLER] Petición POST para publicar reseña");
        Resenia nueva = reseniaService.crearResenia(dto);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @GetMapping("/videojuego/{videojuegoId}")
    public ResponseEntity<List<Resenia>> listarPorJuego(@PathVariable Long videojuegoId) {
        log.info("[RESENIA-CONTROLLER] Petición GET para listar opiniones del juego: {}", videojuegoId);
        List<Resenia> lista = reseniaService.obtenerPorVideojuego(videojuegoId);
        return ResponseEntity.ok(lista);
    }
}