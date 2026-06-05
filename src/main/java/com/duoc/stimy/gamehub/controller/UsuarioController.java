package com.duoc.stimy.gamehub.controller;

// 1. IMPORTS UNIFICADOS: Apuntando al paquete definitivo de tus compañeros
import com.duoc.stimy.gamehub.dto.UsuarioRequestDTO;
import com.duoc.stimy.gamehub.model.Usuario;
import com.duoc.stimy.gamehub.service.UsuarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioRequestDTO dto) {
        log.info("Controller: Recibiendo petición POST para crear el usuario: {}", dto.getNombreUsuario());
        Usuario usuarioCreado = usuarioService.crearUsuario(dto);
        return new ResponseEntity<>(usuarioCreado, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        log.info("Controller: Recibiendo petición GET para listar todos los usuarios");
        List<Usuario> lista = usuarioService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}