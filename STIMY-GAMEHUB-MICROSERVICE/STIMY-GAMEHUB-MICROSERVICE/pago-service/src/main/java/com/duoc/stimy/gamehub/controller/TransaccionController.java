package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.PagoRequestDTO;
import com.duoc.stimy.gamehub.model.Transaccion;
import com.duoc.stimy.gamehub.service.TransaccionService;
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
public class TransaccionController {
    private static final Logger log = LoggerFactory.getLogger(TransaccionController.class);

    @Autowired
    private TransaccionService transaccionService;

    @PostMapping("/procesar")
    public ResponseEntity<Transaccion> pagar(@Valid @RequestBody PagoRequestDTO dto) {
        log.info("[PAGO-CONTROLLER] Petición POST recibida para procesar pasarela de pago");
        Transaccion resultado = transaccionService.procesarPago(dto);
        return new ResponseEntity<>(resultado, HttpStatus.CREATED);
    }

    @GetMapping("/historial/usuario/{usuarioId}")
    public ResponseEntity<List<Transaccion>> verHistorial(@PathVariable Long usuarioId) {
        log.info("[PAGO-CONTROLLER] Petición GET para ver historial del usuario ID: {}", usuarioId);
        List<Transaccion> lista = transaccionService.obtenerHistorialPorUsuario(usuarioId);
        return ResponseEntity.ok(lista);
    }
}