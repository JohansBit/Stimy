package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.model.Videojuego;
import com.duoc.stimy.gamehub.repository.VideojuegoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VideojuegoServiceTest {

	@InjectMocks
	private VideojuegoService videojuegoService;

	@Mock
	private VideojuegoRepository videojuegoRepository;

	@Test
	public void testGuardarVideojuego() {
		// ========== 1. ARRANGE ==========
		Videojuego juegoInput = new Videojuego();
		juegoInput.setTitulo("Elden Ring");
		juegoInput.setPrecio(59.99);

		Videojuego juegoGuardado = new Videojuego();
		juegoGuardado.setId(50L);
		juegoGuardado.setTitulo("Elden Ring");
		juegoGuardado.setPrecio(59.99);

		when(videojuegoRepository.save(juegoInput)).thenReturn(juegoGuardado);

		// ========== 2. ACT ==========
		Videojuego resultado = videojuegoService.guardarVideojuego(juegoInput);

		// ========== 3. ASSERT ==========
		assertNotNull(resultado);
		assertEquals(50L, resultado.getId());
		assertEquals("Elden Ring", resultado.getTitulo());
		assertEquals(59.99, resultado.getPrecio());
		verify(videojuegoRepository, times(1)).save(juegoInput);
	}

	@Test
	public void testListarTodos() {
		// ========== 1. ARRANGE ==========
		Videojuego juego1 = new Videojuego();
		juego1.setId(10L);
		juego1.setTitulo("Hollow Knight");

		Videojuego juego2 = new Videojuego();
		juego2.setId(20L);
		juego2.setTitulo("Hades II");

		List<Videojuego> catalogoFake = new ArrayList<>();
		catalogoFake.add(juego1);
		catalogoFake.add(juego2);

		when(videojuegoRepository.findAll()).thenReturn(catalogoFake);

		// ========== 2. ACT ==========
		List<Videojuego> resultado = videojuegoService.listarTodos();

		// ========== 3. ASSERT ==========
		assertNotNull(resultado);
		assertEquals(2, resultado.size());
		assertEquals("Hollow Knight", resultado.get(0).getTitulo());
		verify(videojuegoRepository, times(1)).findAll();
	}

	@Test
	public void testBuscarPorId_Encontrado() {
		// ========== 1. ARRANGE ==========
		Videojuego juegoMock = new Videojuego();
		juegoMock.setId(100L);
		juegoMock.setTitulo("The Witcher 3");

		when(videojuegoRepository.findById(100L)).thenReturn(Optional.of(juegoMock));

		// ========== 2. ACT ==========
		Videojuego resultado = videojuegoService.buscarPorId(100L);

		// ========== 3. ASSERT ==========
		assertNotNull(resultado);
		assertEquals(100L, resultado.getId());
		assertEquals("The Witcher 3", resultado.getTitulo());
		verify(videojuegoRepository, times(1)).findById(100L);
	}

	@Test
	public void testBuscarPorId_NoEncontrado() {
		// ========== 1. ARRANGE ==========
		when(videojuegoRepository.findById(888L)).thenReturn(Optional.empty());

		// ========== 2. ACT ==========
		Videojuego resultado = videojuegoService.buscarPorId(888L);

		// ========== 3. ASSERT ==========
		assertNull(resultado);
		verify(videojuegoRepository, times(1)).findById(888L);
	}
}