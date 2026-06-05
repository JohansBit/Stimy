package com.duoc.stimy.gamehub.controller; // CORREGIDO: Ahora coincide con la carpeta física

import com.duoc.stimy.gamehub.dto.TicketRequestDTO;
import com.duoc.stimy.gamehub.model.TicketSoporte;
import com.duoc.stimy.gamehub.service.TicketSoporteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soporte")
public class TicketSoporteController {

    private static final Logger log = LoggerFactory.getLogger(TicketSoporteController.class);

    @Autowired
    private TicketSoporteService ticketService;

    @PostMapping("/crear")
    public ResponseEntity<TicketSoporte> crearTicket(@Valid @RequestBody TicketRequestDTO dto) {
        log.info("Controller: Recibiendo petición POST para crear ticket de soporte para el usuario ID: {}", dto.getUsuarioId());
        TicketSoporte ticketCreado = ticketService.crearTicket(dto.getUsuarioId(), dto.getAsunto(), dto.getDescripcion());
        return new ResponseEntity<>(ticketCreado, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<TicketSoporte>> listarPorUsuario(@PathVariable Long usuarioId) {
        log.info("Controller: Recibiendo petición GET de tickets para el usuario ID: {}", usuarioId);
        List<TicketSoporte> tickets = ticketService.listarTicketsPorUsuario(usuarioId);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @PutMapping("/actualizar/{ticketId}")
    public ResponseEntity<TicketSoporte> actualizarEstado(
            @PathVariable Long ticketId,
            @RequestParam String nuevoEstado) {
        log.info("Controller: Recibiendo petición PUT para actualizar ticket ID: {} a estado: {}", ticketId, nuevoEstado);
        TicketSoporte ticketActualizado = ticketService.actualizarEstado(ticketId, nuevoEstado);
        return new ResponseEntity<>(ticketActualizado, HttpStatus.OK);
    }
}