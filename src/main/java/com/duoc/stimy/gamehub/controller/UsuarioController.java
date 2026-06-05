package com.duoc.stimy.gamehub.controller;


import com.duoc.stimy.base_service.dto.UsuarioRequestDTO;
import com.duoc.stimy.base_service.model.Usuario;
import com.duoc.stimy.base_service.service.UsuarioService;
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

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody UsuarioRequestDTO dto) {

        log.info("Controller: Recibiendo petición POST para crear usuario: {}", dto.getNombreUsuario());
        Usuario creado = usuarioService.crearUsuario(dto);
        return new ResponseEntity<>(creado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios() {
        log.info("Controller: Recibiendo petición GET para listar todos los usuarios");
        List<Usuario> lista = usuarioService.obtenerTodos();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}