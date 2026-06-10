package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.model.Videojuego;
import com.duoc.stimy.gamehub.service.VideojuegoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {
    private static final Logger log = LoggerFactory.getLogger(VideojuegoController.class);

    @Autowired
    private VideojuegoService videojuegoService;

    @PostMapping
    public ResponseEntity<Videojuego> crear(@RequestBody Videojuego juego) {
        log.info("[VIDEOJUEGO-CONTROLLER] Solicitud POST para registrar nuevo titulo");
        Videojuego nuevo = videojuegoService.guardarVideojuego(juego);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Videojuego>> listar() {
        log.info("[VIDEOJUEGO-CONTROLLER] Solicitud GET para listar catalogo");
        return ResponseEntity.ok(videojuegoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Videojuego> obtenerPorId(@PathVariable Long id) {
        log.info("[VIDEOJUEGO-CONTROLLER] Solicitud GET para buscar juego ID: {}", id);
        Videojuego juego = videojuegoService.buscarPorId(id);
        if (juego == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(juego);
    }
}