package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.VideojuegoRequestDTO;
import com.duoc.stimy.gamehub.model.Videojuego;
import com.duoc.stimy.gamehub.service.VideojuegoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videojuegos")
public class VideojuegoController {

    @Autowired
    private VideojuegoService videojuegoService;

    @PostMapping
    public ResponseEntity<Videojuego> crearVideojuego(@Valid @RequestBody VideojuegoRequestDTO dto) {
        Videojuego nuevoJuego = videojuegoService.crearVideojuego(dto);
        return new ResponseEntity<>(nuevoJuego, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Videojuego>> obtenerTodos() {
        List<Videojuego> lista = videojuegoService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}