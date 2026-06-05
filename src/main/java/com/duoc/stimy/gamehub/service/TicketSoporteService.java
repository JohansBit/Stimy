package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.base_service.model.TicketSoporte;
import com.duoc.stimy.base_service.model.Usuario;
import com.duoc.stimy.base_service.repository.TicketSoporteRepository;
import com.duoc.stimy.base_service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketSoporteService {

    @Autowired
    private TicketSoporteRepository ticketRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public TicketSoporte crearTicket(Integer usuarioId, String asunto, String descripcion) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TicketSoporte ticket = new TicketSoporte();
        ticket.setUsuario(usuario);
        ticket.setAsunto(asunto);
        ticket.setDescripcion(descripcion);

        return ticketRepository.save(ticket);
    }


    public List<TicketSoporte> listarTicketsPorUsuario(Integer usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return ticketRepository.findByUsuario(usuario);
    }


    public TicketSoporte actualizarEstado(Integer ticketId, String nuevoEstado) {
        TicketSoporte ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
        ticket.setEstado(nuevoEstado);
        return ticketRepository.save(ticket);
    }
}