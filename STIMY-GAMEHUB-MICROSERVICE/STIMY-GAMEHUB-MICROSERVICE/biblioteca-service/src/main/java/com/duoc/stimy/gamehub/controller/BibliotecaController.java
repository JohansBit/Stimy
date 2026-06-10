package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.BibliotecaRequestDTO;
import com.duoc.stimy.gamehub.model.Biblioteca;
import com.duoc.stimy.gamehub.service.BibliotecaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bibliotecas")
public class BibliotecaController {
    private static final Logger log = LoggerFactory.getLogger(BibliotecaController.class);

    @Autowired
    private BibliotecaService bibliotecaService;

    @PostMapping("/canjear")
    public ResponseEntity<Biblioteca> canjear(@Valid @RequestBody BibliotecaRequestDTO dto) {
        log.info("[BIBLIOTECA-CONTROLLER] Recibido POST para añadir juego a propiedad de usuario");
        Biblioteca nueva = bibliotecaService.agregarJuegoABiblioteca(dto);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Biblioteca>> verBiblioteca(@PathVariable Long usuarioId) {
        log.info("[BIBLIOTECA-CONTROLLER] Recibido GET para listar coleccion del usuario ID: {}", usuarioId);
        List<Biblioteca> coleccion = bibliotecaService.obtenerColeccionUsuario(usuarioId);
        return ResponseEntity.ok(coleccion);
    }
}