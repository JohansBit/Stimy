package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.model.Usuario;
import com.duoc.stimy.gamehub.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuarios", description = "Controlador para el registro, consulta y administración de las cuentas de usuario de la plataforma")
public class UsuarioController {
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Operation(
            summary = "Registrar un nuevo usuario",
            description = "Recibe los datos completos de una cuenta en el cuerpo de la petición para dar de alta a un nuevo usuario en el sistema."
    )
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        log.info("[USUARIO-CONTROLLER] Recibida petición POST para registrar usuario");
        Usuario nuevo = usuarioService.guardarUsuario(usuario);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    @Operation(
            summary = "Listar todos los usuarios registrados",
            description = "Retorna una lista completa con todas las cuentas de usuario almacenadas en el sistema, ideal para auditorías de administración."
    )
    public ResponseEntity<List<Usuario>> listar() {
        log.info("[USUARIO-CONTROLLER] Recibida petición GET para listar todos los usuarios");
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar un usuario por su ID",
            description = "Busca en la base de datos una cuenta específica usando el identificador numérico enviado en la URL. Retorna 404 si no existe."
    )
    public ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id) {
        log.info("[USUARIO-CONTROLLER] Recibida petición GET para buscar usuario ID: {}", id);
        Usuario usuario = usuarioService.buscarPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
}