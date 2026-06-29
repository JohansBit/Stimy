package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.VideojuegoClient;
import com.duoc.stimy.gamehub.dto.BibliotecaRequestDTO;
import com.duoc.stimy.gamehub.dto.VideojuegoRemotoDTO;
import com.duoc.stimy.gamehub.model.Biblioteca;
import com.duoc.stimy.gamehub.repository.BibliotecaRepository;
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
public class BibliotecaServiceTest {

    @InjectMocks
    private BibliotecaService bibliotecaService;

    @Mock
    private BibliotecaRepository bibliotecaRepository;

    @Mock
    private VideojuegoClient videojuegoClient;

    @Test
    public void testObtenerColeccionUsuario() {
        // ========== 1. ARRANGE ==========
        Biblioteca b1 = new Biblioteca();
        b1.setId(1L);
        b1.setUsuarioId(1L);
        b1.setVideojuegoId(101L);
        b1.setHorasJugadas(120.5);
        b1.setFechaAdquisicion(LocalDate.parse("2023-01-10"));

        List<Biblioteca> coleccionFalsa = new ArrayList<>();
        coleccionFalsa.add(b1);

        when(bibliotecaRepository.findByUsuarioId(1L)).thenReturn(coleccionFalsa);

        // ========== 2. ACT ==========
        List<Biblioteca> resultado = bibliotecaService.obtenerColeccionUsuario(1L);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(101L, resultado.get(0).getVideojuegoId());

        verify(bibliotecaRepository, times(1)).findByUsuarioId(1L);
    }

    @Test
    public void testAgregarJuegoABiblioteca_Exito() {
        // ========== 1. ARRANGE ==========
        BibliotecaRequestDTO dto = new BibliotecaRequestDTO();
        dto.setUsuarioId(1L);
        dto.setVideojuegoId(101L);

        VideojuegoRemotoDTO juegoRemotoFalso = new VideojuegoRemotoDTO();

        when(videojuegoClient.obtenerVideojuegoPorId(101L)).thenReturn(juegoRemotoFalso);

        Biblioteca bibliotecaGuardadaFalsa = new Biblioteca();
        bibliotecaGuardadaFalsa.setId(1L);
        bibliotecaGuardadaFalsa.setUsuarioId(1L);
        bibliotecaGuardadaFalsa.setVideojuegoId(101L);
        bibliotecaGuardadaFalsa.setHorasJugadas(0.0);

        when(bibliotecaRepository.save(any(Biblioteca.class))).thenReturn(bibliotecaGuardadaFalsa);

        // ========== 2. ACT ==========
        Biblioteca resultado = bibliotecaService.agregarJuegoABiblioteca(dto);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals(101L, resultado.getVideojuegoId());
        assertEquals(0.0, resultado.getHorasJugadas());

        verify(videojuegoClient, times(1)).obtenerVideojuegoPorId(101L);
        verify(bibliotecaRepository, times(1)).save(any(Biblioteca.class));
    }

    @Test
    public void testAgregarJuegoABiblioteca_ErrorJuegoNoExiste() {
        // ========== 1. ARRANGE ==========
        BibliotecaRequestDTO dto = new BibliotecaRequestDTO();
        dto.setUsuarioId(1L);
        dto.setVideojuegoId(999L);

        when(videojuegoClient.obtenerVideojuegoPorId(999L)).thenReturn(null);

        // ========== 2. ACT & ASSERT ==========
        RuntimeException excepcion = assertThrows(RuntimeException.class, () -> {
            bibliotecaService.agregarJuegoABiblioteca(dto);
        });

        assertEquals("No se puede añadir un juego inexistente a la biblioteca.", excepcion.getMessage());
        verify(bibliotecaRepository, never()).save(any(Biblioteca.class));
    }
}