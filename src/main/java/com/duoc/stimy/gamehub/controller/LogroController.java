package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.LogroRequestDTO;
import com.duoc.stimy.gamehub.model.Logro;
import com.duoc.stimy.gamehub.service.LogroService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logros")
public class LogroController {

    private static final Logger log = LoggerFactory.getLogger(LogroController.class);

    @Autowired
    private LogroService logroService;

    @PostMapping
    public ResponseEntity<Logro> crearLogro(@Valid @RequestBody LogroRequestDTO dto) {
        log.info("Controller: Recibiendo petición POST para crear el logro: {}", dto.getNombre());
        Logro logroCreado = logroService.crearLogro(dto);
        return new ResponseEntity<>(logroCreado, HttpStatus.CREATED);
    }

    @GetMapping("/videojuego/{videojuegoId}")
    public ResponseEntity<List<Logro>> obtenerLogrosPorVideojuego(@PathVariable Long videojuegoId) {
        log.info("Controller: Recibiendo petición GET de logros para el juego ID: {}", videojuegoId);
        List<Logro> logros = logroService.obtenerLogrosPorVideojuego(videojuegoId);
        return new ResponseEntity<>(logros, HttpStatus.OK);
    }
}