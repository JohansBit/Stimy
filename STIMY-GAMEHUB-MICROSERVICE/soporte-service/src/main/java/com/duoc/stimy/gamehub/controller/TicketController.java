package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.TicketRequestDTO;
import com.duoc.stimy.gamehub.model.Ticket;
import com.duoc.stimy.gamehub.service.TicketService;
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
@RequestMapping("/api/soporte")
@Tag(name = "Soporte", description = "Controlador para la gestión y seguimiento de tickets de asistencia técnica de los usuarios")
public class TicketController {
    private static final Logger log = LoggerFactory.getLogger(TicketController.class);

    @Autowired
    private TicketService ticketService;

    @PostMapping("/ticket")
    @Operation(
            summary = "Abrir un nuevo ticket de soporte",
            description = "Recibe los detalles del problema o consulta mediante un DTO para registrar un ticket de asistencia en el sistema."
    )
    public ResponseEntity<Ticket> abrirTicket(@Valid @RequestBody TicketRequestDTO dto) {
        log.info("[SOPORTE-CONTROLLER] Procesando apertura de ticket");
        Ticket nuevo = ticketService.crearTicket(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}")
    @Operation(
            summary = "Listar el historial de tickets de un usuario",
            description = "Busca y devuelve todas las solicitudes de asistencia que han sido abiertas por el ID de usuario provisto."
    )
    public ResponseEntity<List<Ticket>> listarTickets(@PathVariable Long usuarioId) {
        log.info("[SOPORTE-CONTROLLER] Solicitud para ver historial de tickets");
        List<Ticket> lista = ticketService.listarPorUsuario(usuarioId);
        return ResponseEntity.ok(lista);
    }
}