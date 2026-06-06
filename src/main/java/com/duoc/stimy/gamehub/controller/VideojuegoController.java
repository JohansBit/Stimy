package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.VideojuegoRequestDTO;
import com.duoc.stimy.gamehub.model.Videojuego;
import com.duoc.stimy.gamehub.service.VideojuegoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Videojuego> crearVideojuego(@Valid @RequestBody VideojuegoRequestDTO dto) {
        log.info("Controller: Recibiendo petición POST para crear videojuego: {}", dto.getTitulo());
        Videojuego creado = videojuegoService.crearVideojuego(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Videojuego>> obtenerVideojuegos() {
        log.info("Controller: Recibiendo petición GET para listar todos los videojuegos");
        List<Videojuego> lista = videojuegoService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}