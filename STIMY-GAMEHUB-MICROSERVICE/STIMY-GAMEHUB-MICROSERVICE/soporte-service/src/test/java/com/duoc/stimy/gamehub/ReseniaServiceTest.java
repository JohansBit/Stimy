package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.VideojuegoClient;
import com.duoc.stimy.gamehub.dto.ReseniaRequestDTO;
import com.duoc.stimy.gamehub.dto.VideojuegoRemotoDTO;
import com.duoc.stimy.gamehub.model.Resenia;
import com.duoc.stimy.gamehub.repository.ReseniaRepository;
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
public class ReseniaServiceTest {

    @InjectMocks
    private ReseniaService reseniaService;

    @Mock
    private ReseniaRepository reseniaRepository;

    @Mock
    private VideojuegoClient videojuegoClient;

    @Test
    public void testCrearResenia_Exito() {
        // ========== 1. ARRANGE ==========
        ReseniaRequestDTO dto = new ReseniaRequestDTO();
        dto.setCalificacion(5);
        dto.setComentario("Una obra maestra de principio a fin.");
        dto.setVideojuegoId(10L);
        dto.setUsuarioId(2L);

        VideojuegoRemotoDTO juegoRemoto = new VideojuegoRemotoDTO();
        juegoRemoto.setTitulo("Elden Ring");

        when(videojuegoClient.obtenerVideojuegoPorId(10L)).thenReturn(juegoRemoto);

        Resenia reseniaGuardada = new Resenia();
        reseniaGuardada.setId(1001L);
        reseniaGuardada.setCalificacion(dto.getCalificacion());
        reseniaGuardada.setComentario(dto.getComentario());
        reseniaGuardada.setVideojuegoId(dto.getVideojuegoId());
        reseniaGuardada.setUsuarioId(dto.getUsuarioId());
        reseniaGuardada.setFechaPublicacion(LocalDate.now());

        when(reseniaRepository.save(any(Resenia.class))).thenReturn(reseniaGuardada);

        // ========== 2. ACT ==========
        Resenia resultado = reseniaService.crearResenia(dto);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals(1001L, resultado.getId());
        assertEquals(5, resultado.getCalificacion());
        assertEquals("Una obra maestra de principio a fin.", resultado.getComentario());

        verify(videojuegoClient, times(1)).obtenerVideojuegoPorId(10L);
        verify(reseniaRepository, times(1)).save(any(Resenia.class));
    }

    @Test
    public void testCrearResenia_ErrorJuegoNoExiste() {
        // ========== 1. ARRANGE ==========
        ReseniaRequestDTO dto = new ReseniaRequestDTO();
        dto.setCalificacion(4);
        dto.setComentario("Buen intento");
        dto.setVideojuegoId(999L);
        dto.setUsuarioId(2L);

        when(videojuegoClient.obtenerVideojuegoPorId(999L)).thenReturn(null);

        // ========== 2. ACT & ASSERT ==========
        RuntimeException excepcion = assertThrows(RuntimeException.class, () ->
                reseniaService.crearResenia(dto)
        );

        assertEquals("No se puede comentar un videojuego inexistente.", excepcion.getMessage());
        verify(reseniaRepository, never()).save(any(Resenia.class));
    }

    @Test
    public void testObtenerPorVideojuego() {
        // ========== 1. ARRANGE ==========
        Resenia r = new Resenia();
        r.setId(50L);
        r.setComentario("Divertido");
        r.setVideojuegoId(10L);

        List<Resenia> listaFalsa = new ArrayList<>();
        listaFalsa.add(r);

        when(reseniaRepository.findByVideojuegoId(10L)).thenReturn(listaFalsa);

        // ========== 2. ACT ==========
        List<Resenia> resultado = reseniaService.obtenerPorVideojuego(10L);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Divertido", resultado.get(0).getComentario());
        verify(reseniaRepository, times(1)).findByVideojuegoId(10L);
    }
}