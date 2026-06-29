package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.VideojuegoClient;
import com.duoc.stimy.gamehub.dto.LogroRequestDTO;
import com.duoc.stimy.gamehub.dto.VideojuegoRemotoDTO;
import com.duoc.stimy.gamehub.model.Logro;
import com.duoc.stimy.gamehub.repository.LogroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogroServiceTest {

    @InjectMocks
    private LogroService logroService;

    @Mock
    private LogroRepository logroRepository;

    @Mock
    private VideojuegoClient videojuegoClient;

    @Test
    public void testCrearLogro_Exito() {
        // ========== 1. ARRANGE ==========
        LogroRequestDTO dto = new LogroRequestDTO();
        dto.setNombre("Primeros Pasos");
        dto.setDescripcion("Completa el tutorial del juego");
        dto.setPuntosRecompensa(15);
        dto.setVideojuegoId(404L);

        VideojuegoRemotoDTO juegoRemoto = new VideojuegoRemotoDTO();
        juegoRemoto.setTitulo("Hollow Knight");

        when(videojuegoClient.obtenerVideojuegoPorId(404L)).thenReturn(juegoRemoto);

        Logro logroGuardado = new Logro();
        logroGuardado.setId(1L);
        logroGuardado.setNombre(dto.getNombre());
        logroGuardado.setDescripcion(dto.getDescripcion());
        logroGuardado.setPuntosRecompensa(dto.getPuntosRecompensa());
        logroGuardado.setVideojuegoId(dto.getVideojuegoId());

        when(logroRepository.save(any(Logro.class))).thenReturn(logroGuardado);

        // ========== 2. ACT ==========
        Logro resultado = logroService.crearLogro(dto);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals("Primeros Pasos", resultado.getNombre());
        assertEquals(404L, resultado.getVideojuegoId());

        verify(videojuegoClient, times(1)).obtenerVideojuegoPorId(404L);
        verify(logroRepository, times(1)).save(any(Logro.class));
    }

    @Test
    public void testCrearLogro_ErrorJuegoNoExiste() {
        // ========== 1. ARRANGE ==========
        LogroRequestDTO dto = new LogroRequestDTO();
        dto.setNombre("Inalcanzable");
        dto.setVideojuegoId(999L);

        when(videojuegoClient.obtenerVideojuegoPorId(999L)).thenReturn(null);

        // ========== 2. ACT & ASSERT ==========
        RuntimeException excepcion = assertThrows(RuntimeException.class, () ->
                logroService.crearLogro(dto)
        );

        assertEquals("El videojuego para este logro no existe.", excepcion.getMessage());
        verify(logroRepository, never()).save(any(Logro.class));
    }

    @Test
    public void testObtenerPorVideojuego() {
        // ========== 1. ARRANGE ==========
        Logro logro = new Logro();
        logro.setId(1L);
        logro.setNombre("Platinado");
        logro.setVideojuegoId(404L);

        List<Logro> listaFalsa = new ArrayList<>();
        listaFalsa.add(logro);

        when(logroRepository.findByVideojuegoId(404L)).thenReturn(listaFalsa);

        // ========== 2. ACT ==========
        List<Logro> resultado = logroService.obtenerPorVideojuego(404L);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Platinado", resultado.get(0).getNombre());
        verify(logroRepository, times(1)).findByVideojuegoId(404L);
    }
}