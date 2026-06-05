package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.AmigoRequestDTO;
import com.duoc.stimy.gamehub.model.Usuario;
import com.duoc.stimy.gamehub.service.AmigosService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/amigos")
public class AmigosController {

    private static final Logger log = LoggerFactory.getLogger(AmigosController.class);

    @Autowired
    private AmigosService amigosService;

    @PostMapping("/agregar")
    public ResponseEntity<String> agregarAmigo(@Valid @RequestBody AmigoRequestDTO dto) {
        log.info("Controller: Recibiendo petición POST para agregar amigo. Usuario ID: {}, Amigo ID: {}", dto.getUsuarioId(), dto.getAmigoId());
        amigosService.agregarAmigo(dto.getUsuarioId(), dto.getAmigoId());
        return new ResponseEntity<>("Solicitud de amigo procesada con exito", HttpStatus.CREATED);
    }

    @GetMapping("/listar/{usuarioId}")
    public ResponseEntity<List<Usuario>> listarAmigos(@PathVariable Long usuarioId) {
        log.info("Controller: Recibiendo petición GET para listar amigos del usuario ID: {}", usuarioId);
        List<Usuario> amigos = amigosService.listarAmigos(usuarioId);
        return new ResponseEntity<>(amigos, HttpStatus.OK);
    }
}