package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.model.TicketSoporte;
import com.duoc.stimy.gamehub.repository.TicketSoporteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketSoporteService {

    private static final Logger log = LoggerFactory.getLogger(TicketSoporteService.class);

    @Autowired
    private TicketSoporteRepository ticketRepository;

    // Cambiado a Long usuarioId
    public TicketSoporte crearTicket(Long usuarioId, String asunto, String descripcion) {
        log.info("Service: Creando ticket para el usuario ID: {}", usuarioId);
        TicketSoporte ticket = new TicketSoporte();
        ticket.setUsuarioId(usuarioId);
        ticket.setAsunto(asunto);
        ticket.setDescripcion(descripcion);
        ticket.setEstado("ABIERTO");
        return ticketRepository.save(ticket);
    }

    // Cambiado a Long usuarioId
    public List<TicketSoporte> listarTicketsPorUsuario(Long usuarioId) {
        log.info("Service: Buscando tickets del usuario ID: {}", usuarioId);
        return ticketRepository.findByUsuarioId(usuarioId);
    }

    // Cambiado a Long ticketId
    public TicketSoporte actualizarEstado(Long ticketId, String nuevoEstado) {
        log.info("Service: Actualizando ticket ID: {} a estado: {}", ticketId, nuevoEstado);
        TicketSoporte ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        ticket.setEstado(nuevoEstado);
        return ticketRepository.save(ticket);
    }
}