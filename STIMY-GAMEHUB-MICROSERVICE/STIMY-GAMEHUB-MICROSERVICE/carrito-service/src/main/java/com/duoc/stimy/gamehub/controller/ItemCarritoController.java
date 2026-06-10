package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.CarritoRequestDTO;
import com.duoc.stimy.gamehub.model.ItemCarrito;
import com.duoc.stimy.gamehub.service.ItemCarritoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class ItemCarritoController {
    private static final Logger log = LoggerFactory.getLogger(ItemCarritoController.class);

    @Autowired
    private ItemCarritoService itemCarritoService;

    @PostMapping("/agregar")
    public ResponseEntity<ItemCarrito> agregarAlCarrito(@Valid @RequestBody CarritoRequestDTO dto) {
        log.info("[CARRITO-CONTROLLER] Solicitud POST recibida para agregar item al usuario: {}", dto.getUsuarioId());
        ItemCarrito nuevoItem = itemCarritoService.agregarItem(dto);
        return new ResponseEntity<>(nuevoItem, HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<ItemCarrito>> obtenerCarrito(@PathVariable Long usuarioId) {
        log.info("[CARRITO-CONTROLLER] Solicitud GET recibida para listar carrito del usuario: {}", usuarioId);
        List<ItemCarrito> items = itemCarritoService.obtenerCarritoPorUsuario(usuarioId);
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{usuarioId}/vaciar")
    public ResponseEntity<Void> vaciarCarrito(@PathVariable Long usuarioId) {
        log.info("[CARRITO-CONTROLLER] Solicitud DELETE recibida para vaciar el carrito del usuario: {}", usuarioId);
        itemCarritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.noContent().build();
    }
}