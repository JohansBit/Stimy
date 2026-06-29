package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.BibliotecaRequestDTO;
import com.duoc.stimy.gamehub.model.Biblioteca;
import com.duoc.stimy.gamehub.service.BibliotecaService;

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
@RequestMapping("/api/bibliotecas")
@Tag(name= "Biblioteca", description = "Controlador para la gestión de las colecciones de videojuegos adquiridas por los usuarios")
public class BibliotecaController {
    private static final Logger log = LoggerFactory.getLogger(BibliotecaController.class);

    @Autowired
    private BibliotecaService bibliotecaService;

    @PostMapping("/canjear")
    @Operation(
            summary = "Canjear o añadir un juego a la biblioteca",
            description = "Recibe un DTO con el ID del usuario y del videojuego para registrarlo como propiedad del usuario en su biblioteca virtual."
    )
    public ResponseEntity<Biblioteca> canjear(@Valid @RequestBody BibliotecaRequestDTO dto) {
        log.info("[BIBLIOTECA-CONTROLLER] Recibido POST para añadir juego a propiedad de usuario");
        Biblioteca nueva = bibliotecaService.agregarJuegoABiblioteca(dto);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(
            summary = "Listar la colección de un usuario",
            description = "Busca y retorna todos los registros de la biblioteca que pertenezcan al ID del usuario ingresado en la URL."
    )
    public ResponseEntity<List<Biblioteca>> verBiblioteca(@PathVariable Long usuarioId) {
        log.info("[BIBLIOTECA-CONTROLLER] Recibido GET para listar coleccion del usuario ID: {}", usuarioId);
        List<Biblioteca> coleccion = bibliotecaService.obtenerColeccionUsuario(usuarioId);
        return ResponseEntity.ok(coleccion);
    }
}