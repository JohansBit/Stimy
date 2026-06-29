package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.UsuarioClient;
import com.duoc.stimy.gamehub.dto.TicketRequestDTO;
import com.duoc.stimy.gamehub.dto.UsuarioRemotoDTO;
import com.duoc.stimy.gamehub.model.Ticket;
import com.duoc.stimy.gamehub.repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

	@InjectMocks
	private TicketService ticketService;

	@Mock
	private TicketRepository ticketRepository;

	@Mock
	private UsuarioClient usuarioClient;

	@Test
	public void testCrearTicket_Exito() {
		// ========== 1. ARRANGE ==========
		TicketRequestDTO dto = new TicketRequestDTO();
		dto.setUsuarioId(45L);
		dto.setAsunto("Fallo en transacción");
		dto.setDescripcion("El juego cobró pero no aparece en mi biblioteca.");

		UsuarioRemotoDTO usuarioFake = new UsuarioRemotoDTO();

		when(usuarioClient.obtenerUsuarioPorId(45L)).thenReturn(usuarioFake);

		Ticket ticketGuardado = new Ticket();
		ticketGuardado.setId(777L);
		ticketGuardado.setAsunto(dto.getAsunto());
		ticketGuardado.setDescripcion(dto.getDescripcion());
		ticketGuardado.setUsuarioId(dto.getUsuarioId());
		ticketGuardado.setEstado("ABIERTO");
		ticketGuardado.setFechaCreacion(LocalDate.now());

		when(ticketRepository.save(any(Ticket.class))).thenReturn(ticketGuardado);

		// ========== 2. ACT ==========
		Ticket resultado = ticketService.crearTicket(dto);

		// ========== 3. ASSERT ==========
		assertNotNull(resultado);
		assertEquals(777L, resultado.getId());
		assertEquals("ABIERTO", resultado.getEstado());
		assertEquals("Fallo en transacción", resultado.getAsunto());

		verify(usuarioClient, times(1)).obtenerUsuarioPorId(45L);
		verify(ticketRepository, times(1)).save(any(Ticket.class));
	}

	@Test
	public void testCrearTicket_ErrorUsuarioNoExiste() {
		// ========== 1. ARRANGE ==========
		TicketRequestDTO dto = new TicketRequestDTO();
		dto.setUsuarioId(999L);
		dto.setAsunto("Hacker detectado");
		dto.setDescripcion("Vuelo ilegal en sala de chat.");

		when(usuarioClient.obtenerUsuarioPorId(999L)).thenReturn(null);

		// ========== 2. ACT & ASSERT ==========
		RuntimeException excepcion = assertThrows(RuntimeException.class, () ->
				ticketService.crearTicket(dto)
		);

		assertEquals("Usuario no registrado. No se puede crear el ticket.", excepcion.getMessage());
		verify(ticketRepository, never()).save(any(Ticket.class));
	}

	@Test
	public void testListarPorUsuario() {
		// ========== 1. ARRANGE ==========
		Ticket t = new Ticket();
		t.setId(10L);
		t.setAsunto("Problema técnico");
		t.setUsuarioId(45L);

		List<Ticket> ticketsFake = new ArrayList<>();
		ticketsFake.add(t);

		when(ticketRepository.findByUsuarioId(45L)).thenReturn(ticketsFake);

		// ========== 2. ACT ==========
		List<Ticket> resultado = ticketService.listarPorUsuario(45L);

		// ========== 3. ASSERT ==========
		assertNotNull(resultado);
		assertEquals(1, resultado.size());
		assertEquals("Problema técnico", resultado.get(0).getAsunto());
		verify(ticketRepository, times(1)).findByUsuarioId(45L);
	}
}