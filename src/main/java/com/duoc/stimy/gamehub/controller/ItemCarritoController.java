package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.CarritoRequestDTO;
import com.duoc.stimy.gamehub.model.ItemCarrito;
import com.duoc.stimy.gamehub.service.ItemCarritoService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apí/carrito")
public class ItemCarritoController {

    private static final Logger log = LoggerFactory.getLogger(ItemCarritoController.class);
    private ItemCarritoService itemCarritoService;

    public ItemCarritoController(ItemCarritoService itemCarritoService) {
        this.itemCarritoService = itemCarritoService;
    }

    @PostMapping("/agregar")
    public ResponseEntity<ItemCarrito> agregarAlCarrito(@Valid @RequestBody ItemCarrito itemCarrito){
        log.info("Controller: Recibiendo petición POST validada para agregar juego al carrito");
        ItemCarrito nuevoItem = itemCarritoService.agregarAlCarrito(itemCarrito);
        return new ResponseEntity<>(nuevoItem, HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<CarritoRequestDTO>> obtenerCarritoPorId(@PathVariable Long usuarioId){
        log.info("Controller: REcibiendo peticion GET para mostrar el carrito del Usuario ID: {} ", usuarioId);
        List<CarritoRequestDTO> carrito = itemCarritoService.obtenerCarritoPorUsuario(usuarioId);
        return ResponseEntity.ok(carrito);
    }

    @DeleteMapping("/{usuarioId}/vaciar")
    public ResponseEntity<String> vaciarCarrito(@PathVariable Long usuarioId){
        log.info("Controller: Recibiendo petición DELETE para vaciar carrito del usuario ID: {}", usuarioId);
        itemCarritoService.vaciarCarrito(usuarioId);
        return ResponseEntity.ok("Carrito vaciado con exito para el usuario: " + usuarioId);
    }
}
