package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.model.Videojuego;
import com.duoc.stimy.gamehub.service.VideojuegoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
@Tag(name = "Videojuegos", description = "Controlador para administrar el catálogo global de títulos disponibles en la plataforma")
public class VideojuegoController {
    private static final Logger log = LoggerFactory.getLogger(VideojuegoController.class);

    @Autowired
    private VideojuegoService videojuegoService;

    @PostMapping
    @Operation(
            summary = "Registrar un nuevo videojuego",
            description = "Añade un nuevo título al catálogo global de la tienda enviando los detalles del videojuego en el cuerpo de la solicitud."
    )
    public ResponseEntity<Videojuego> crear(@RequestBody Videojuego juego) {
        log.info("[VIDEOJUEGO-CONTROLLER] Solicitud POST para registrar nuevo titulo");
        Videojuego nuevo = videojuegoService.guardarVideojuego(juego);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Listar todo el catálogo de videojuegos",
            description = "Retorna la lista completa de todos los títulos disponibles en el sistema de GameHub."
    )
    public ResponseEntity<List<Videojuego>> listar() {
        log.info("[VIDEOJUEGO-CONTROLLER] Solicitud GET para listar catalogo");
        return ResponseEntity.ok(videojuegoService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar un videojuego por su ID",
            description = "Busca la ficha técnica de un juego específico a través de su identificador numérico. Retorna 404 si el título no existe."
    )
    public ResponseEntity<Videojuego> obtenerPorId(@PathVariable Long id) {
        log.info("[VIDEOJUEGO-CONTROLLER] Solicitud GET para buscar juego ID: {}", id);
        Videojuego juego = videojuegoService.buscarPorId(id);
        if (juego == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(juego);
    }
}