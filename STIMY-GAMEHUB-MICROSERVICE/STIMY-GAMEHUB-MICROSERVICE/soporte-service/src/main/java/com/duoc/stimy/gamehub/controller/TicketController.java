package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.TicketRequestDTO;
import com.duoc.stimy.gamehub.model.Ticket;
import com.duoc.stimy.gamehub.service.TicketService;
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
public class TicketController {
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @PostMapping("/ticket")
    public ResponseEntity<Ticket> abrirTicket(@Valid @RequestBody TicketRequestDTO dto) {
        log.info("[SOPORTE-CONTROLLER] Procesando apertura de ticket");
        Ticket nuevo = ticketService.crearTicket(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Ticket>> listarTickets(@PathVariable Long usuarioId) {
        log.info("[SOPORTE-CONTROLLER] Solicitud para ver historial de tickets");
        List<Ticket> lista = ticketService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(lista);
    }
}