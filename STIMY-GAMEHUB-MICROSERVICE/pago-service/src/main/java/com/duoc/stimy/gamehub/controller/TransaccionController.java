package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.PagoRequestDTO;
import com.duoc.stimy.gamehub.model.Transaccion;
import com.duoc.stimy.gamehub.service.TransaccionService;
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
@RequestMapping("/api/pagos")
@Tag(name = "Pagos y Transacciones", description = "Controlador para el procesamiento de pagos y consulta del historial de transacciones")
public class TransaccionController {
    private static final Logger log = LoggerFactory.getLogger(TransaccionController.class);

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/procesar")
    @Operation(
            summary = "Procesar el pago de una compra",
            description = "Recibe los datos de la pasarela mediante un DTO para validar el pago y consolidar la compra de los videojuegos."
    )
    public ResponseEntity<Transaccion> pagar(@Valid @RequestBody PagoRequestDTO dto) {
        log.info("[PAGO-CONTROLLER] Petición POST recibida para procesar pasarela de pago");
        Transaccion resultado = transaccionService.procesarPago(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @GetMapping("/historial/usuario/{usuarioId}")
    @Operation(
            summary = "Ver historial de pagos de un usuario",
            description = "Busca y lista todas las transacciones financieras que han sido completadas o registradas bajo el ID de usuario provisto."
    )
    public ResponseEntity<List<Transaccion>> verHistorial(@PathVariable Long usuarioId) {
        log.info("[PAGO-CONTROLLER] Petición GET para ver historial del usuario ID: {}", usuarioId);
        List<Transaccion> lista = transaccionService.obtenerHistorialPorUsuario(usuarioId);
        return ResponseEntity.ok(lista);
    }
}