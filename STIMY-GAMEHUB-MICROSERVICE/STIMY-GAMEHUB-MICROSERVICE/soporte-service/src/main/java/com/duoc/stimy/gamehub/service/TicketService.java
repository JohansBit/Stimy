package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.UsuarioClient;
import com.duoc.stimy.gamehub.dto.TicketRequestDTO;
import com.duoc.stimy.gamehub.dto.UsuarioRemotoDTO;
import com.duoc.stimy.gamehub.model.Ticket;
import com.duoc.stimy.gamehub.repository.TicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TicketService {
    private static final Logger log = LoggerFactory.getLogger(TicketService.class);

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    public Ticket crearTicket(TicketRequestDTO dto) {
        log.info("[SOPORTE-SERVICE] Validando existencia de usuario de forma remota ID: {}", dto.getUsuarioId());

        UsuarioRemotoDTO usuarioRemoto = usuarioClient.obtenerUsuarioPorId(dto.getUsuarioId());

        if (usuarioRemoto == null) {
            log.error("[SOPORTE-SERVICE] El usuario ID {} no existe.", dto.getUsuarioId());
            throw new RuntimeException("Usuario no registrado. No se puede crear el ticket.");
        }

        Ticket ticket = new Ticket();
        ticket.setAsunto(dto.getAsunto());
        ticket.setDescripcion(dto.getDescripcion());
        ticket.setUsuarioId(dto.getUsuarioId());
        ticket.setEstado("ABIERTO");
        ticket.setFechaCreacion(LocalDate.now());

        return ticketRepository.save(ticket);
    }

    public List<Ticket> listarPorUsuario(Long usuarioId) {
        log.info("[SOPORTE-SERVICE] Listando tickets para usuario ID: {}", usuarioId);
        return ticketRepository.findByUsuarioId(usuarioId);
    }
}