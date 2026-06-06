package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.DeseoRequestDTO;
import com.duoc.stimy.gamehub.model.Deseo;
import com.duoc.stimy.gamehub.service.DeseoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deseos")
public class DeseoController {

    private static final Logger log = LoggerFactory.getLogger(DeseoController.class);

    @Autowired
    private DeseoService deseoService;

    @PostMapping
    public ResponseEntity<Deseo> agregarDeseo(@Valid @RequestBody DeseoRequestDTO dto) {
        log.info("Controller: Recibiendo petición POST para agregar juego a la wishlist del usuario ID: {}", dto.getUsuarioId());
        Deseo deseoAgregado = deseoService.agregarDeseo(dto);
        return new ResponseEntity<>(deseoAgregado, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Deseo>> obtenerDeseosPorUsuario(@PathVariable Long usuarioId) {
        log.info("Controller: Recibiendo petición GET de la wishlist para el usuario ID: {}", usuarioId);
        List<Deseo> deseos = deseoService.obtenerDeseosPorUsuario(usuarioId);
        return new ResponseEntity<>(deseos, HttpStatus.OK);
    }
}