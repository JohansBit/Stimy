package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.model.Usuario;
import com.duoc.stimy.gamehub.repository.UsuarioRepository;
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
public class UsuarioServiceTest {

	@InjectMocks
	private UsuarioService usuarioService;

	@Mock
	private UsuarioRepository usuarioRepository;

	@Test
	public void testGuardarUsuario() {
		// ========== 1. ARRANGE ==========
		Usuario usuarioInput = new Usuario();
		usuarioInput.setNombreUsuario("JohansBit");
		usuarioInput.setEmail("johan@stimy.com");

		Usuario usuarioGuardado = new Usuario();
		usuarioGuardado.setId(1L);
		usuarioGuardado.setNombreUsuario("JohansBit");
		usuarioGuardado.setEmail("johan@stimy.com");

		when(usuarioRepository.save(usuarioInput)).thenReturn(usuarioGuardado);

		// ========== 2. ACT ==========
		Usuario resultado = usuarioService.guardarUsuario(usuarioInput);

		// ========== 3. ASSERT ==========
		assertNotNull(resultado);
		assertEquals(1L, resultado.getId());
		assertEquals("JohansBit", resultado.getNombreUsuario());
		verify(usuarioRepository, times(1)).save(usuarioInput);
	}

	@Test
	public void testListarTodos() {
		// ========== 1. ARRANGE ==========
		Usuario user1 = new Usuario();
		user1.setId(1L);
		user1.setNombreUsuario("userOne");

		Usuario user2 = new Usuario();
		user2.setId(2L);
		user2.setNombreUsuario("userTwo");

		List<Usuario> listaFake = new ArrayList<>();
		listaFake.add(user1);
		listaFake.add(user2);

		when(usuarioRepository.findAll()).thenReturn(listaFake);

		// ========== 2. ACT ==========
		List<Usuario> resultado = usuarioService.listarTodos();

		// ========== 3. ASSERT ==========
		assertNotNull(resultado);
		assertEquals(2, resultado.size());
		assertEquals("userOne", resultado.get(0).getNombreUsuario());
		verify(usuarioRepository, times(1)).findAll();
	}

	@Test
	public void testBuscarPorId_Encontrado() {
		// ========== 1. ARRANGE ==========
		Usuario usuarioMock = new Usuario();
		usuarioMock.setId(10L);
		usuarioMock.setNombreUsuario("Francisco");

		when(usuarioRepository.findById(10L)).thenReturn(Optional.of(usuarioMock));

		// ========== 2. ACT ==========
		Usuario resultado = usuarioService.buscarPorId(10L);

		// ========== 3. ASSERT ==========
		assertNotNull(resultado);
		assertEquals(10L, resultado.getId());
		assertEquals("Francisco", resultado.getNombreUsuario());
		verify(usuarioRepository, times(1)).findById(10L);
	}

	@Test
	public void testBuscarPorId_NoEncontrado() {
		// ========== 1. ARRANGE ==========
		when(usuarioRepository.findById(99L)).thenReturn(Optional.empty());

		// ========== 2. ACT ==========
		Usuario resultado = usuarioService.buscarPorId(99L);

		// ========== 3. ASSERT ==========
		assertNull(resultado); // Debe retornar null según la regla .orElse(null)
		verify(usuarioRepository, times(1)).findById(99L);
	}
}