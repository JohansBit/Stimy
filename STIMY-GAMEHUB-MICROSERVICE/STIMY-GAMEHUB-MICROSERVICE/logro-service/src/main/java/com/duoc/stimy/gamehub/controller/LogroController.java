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
    public ResponseEntity<Logro> crear(@Valid @RequestBody LogroRequestDTO dto) {
        log.info("[LOGRO-CONTROLLER] Solicitud POST recibida para registrar logro");
        Logro nuevo = logroService.crearLogro(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("/videojuego/{videojuegoId}")
    public ResponseEntity<List<Logro>> listarPorJuego(@PathVariable Long videojuegoId) {
        log.info("[LOGRO-CONTROLLER] Solicitud GET para obtener logros del juego: {}", videojuegoId);
        List<Logro> logros = logroService.obtenerPorVideojuego(videojuegoId);
        return ResponseEntity.ok(logros);
    }
}