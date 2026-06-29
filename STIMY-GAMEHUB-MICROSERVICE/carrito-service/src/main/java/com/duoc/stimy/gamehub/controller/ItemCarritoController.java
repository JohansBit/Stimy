package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.CarritoRequestDTO;
import com.duoc.stimy.gamehub.model.ItemCarrito;
import com.duoc.stimy.gamehub.service.ItemCarritoService;
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
@RequestMapping("/api/carritos")
@Tag(name = "Carrito", description = "Controlador para gestionar la adición, consulta y limpieza de productos en el carrito de compras")
public class ItemCarritoController {
    private static final Logger log = LoggerFactory.getLogger(ItemCarritoController.class);

    @Autowired
    private ItemCarritoService itemCarritoService;

    @PostMapping("/agregar")
    @Operation(
            summary = "Agregar un videojuego al carrito",
            description = "Recibe los IDs de usuario y videojuego en un DTO para registrar un nuevo ítem o incrementar su presencia en el carrito actual."
    )
    public ResponseEntity<ItemCarrito> agregarAlCarrito(@Valid @RequestBody CarritoRequestDTO dto) {
        log.info("[CARRITO-CONTROLLER] Solicitud POST recibida para agregar item al usuario: {}", dto.getUsuarioId());
        ItemCarrito nuevoItem = itemCarritoService.agregarItem(dto);
        return new ResponseEntity<>(nuevoItem, HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    @Operation(
            summary = "Obtener los ítems del carrito de un usuario",
            description = "Busca y lista todos los videojuegos que el usuario ha añadido temporalmente a su carrito de compras mediante su ID."
    )
    public ResponseEntity<List<ItemCarrito>> obtenerCarrito(@PathVariable Long usuarioId) {
        log.info("[CARRITO-CONTROLLER] Solicitud GET recibida para listar carrito del usuario: {}", usuarioId);
        List<ItemCarrito> items = itemCarritoService.obtenerCarritoPorUsuario(usuarioId);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{usuarioId}/vaciar")
    @Operation(
            summary = "Vaciar por completo el carrito",
            description = "Elimina de forma permanente todos los ítems almacenados en el carrito del usuario especificado, ideal para limpiar tras una compra o cancelación."
    )
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long usuarioId) {
        log.info("[CARRITO-CONTROLLER] Solicitud DELETE recibida para vaciar el carrito del usuario: {}", usuarioId);
        itemCarritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }
}