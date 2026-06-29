package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.DeseoRequestDTO;
import com.duoc.stimy.gamehub.model.ListaDeseos;
import com.duoc.stimy.gamehub.service.ListaDeseosService;
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
@RequestMapping("/api/deseos")
@Tag(name = "Lista de Deseos", description = "Controlador para gestionar los videojuegos que los usuarios añaden a sus listas de interés")
public class ListaDeseosController {
    private static final Logger log = LoggerFactory.getLogger(ListaDeseosController.class);

    @Autowired
    private ListaDeseosService listaDeseosService;

    @PostMapping("/agregar")
    @Operation(
            summary = "Agregar un videojuego a la lista de deseos",
            description = "Recibe el ID del usuario y el ID del videojuego mediante un DTO para guardarlo en su lista de favoritos/interés."
    )
    public ResponseEntity<ListaDeseos> agregar(@Valid @RequestBody DeseoRequestDTO dto) {
        log.info("[DESEO-CONTROLLER] Solicitud POST agregar lista de deseos");
        ListaDeseos nuevo = listaDeseosService.agregarALista(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    @Operation(
            summary = "Obtener la lista de deseos de un usuario",
            description = "Busca y retorna todos los videojuegos que han sido marcados como de interés por el ID de usuario provisto en la URL."
    )
    public ResponseEntity<List<ListaDeseos>> obtener(@PathVariable Long usuarioId) {
        log.info("[DESEO-CONTROLLER] Solicitud GET deseos del usuario ID: {}", usuarioId);
        List<ListaDeseos> lista = listaDeseosService.obtenerPorUsuario(usuarioId);
        return ResponseEntity.ok(lista);
    }
}