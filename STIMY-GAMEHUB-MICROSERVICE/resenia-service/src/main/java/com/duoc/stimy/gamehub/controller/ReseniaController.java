package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.ReseniaRequestDTO;
import com.duoc.stimy.gamehub.model.Resenia;
import com.duoc.stimy.gamehub.service.ReseniaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Reseñas", description = "Controlador para gestionar las opiniones, comentarios y calificaciones de los videojuegos")
public class ReseniaController {
    private static final Logger log = LoggerFactory.getLogger(ReseniaController.class);

    @Autowired
    private ReseniaService reseniaService;

    @PostMapping
    @Operation(
            summary = "Publicar una nueva reseña",
            description = "Registra la opinión y puntuación de un usuario sobre un videojuego específico utilizando los datos proporcionados en el DTO."
    )
    public ResponseEntity<Resenia> agregar(@Valid @RequestBody ReseniaRequestDTO dto) {
        log.info("[RESENIA-CONTROLLER] Petición POST para publicar reseña");
        Resenia nueva = reseniaService.crearResenia(dto);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @GetMapping("/videojuego/{videojuegoId}")
    @Operation(
            summary = "Listar reseñas por videojuego",
            description = "Busca y retorna todas las opiniones, críticas y evaluaciones asignadas al ID del videojuego provisto en la URL."
    )
    public ResponseEntity<List<Resenia>> listarPorJuego(@PathVariable Long videojuegoId) {
        log.info("[RESENIA-CONTROLLER] Petición GET para listar opiniones del juego: {}", videojuegoId);
        List<Resenia> lista = reseniaService.obtenerPorVideojuego(videojuegoId);
        return ResponseEntity.ok(lista);
    }
}