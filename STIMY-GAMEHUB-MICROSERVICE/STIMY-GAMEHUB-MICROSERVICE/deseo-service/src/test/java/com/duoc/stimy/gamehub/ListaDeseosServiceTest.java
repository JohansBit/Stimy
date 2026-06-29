package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.VideojuegoClient;
import com.duoc.stimy.gamehub.dto.DeseoRequestDTO;
import com.duoc.stimy.gamehub.dto.VideojuegoRemotoDTO;
import com.duoc.stimy.gamehub.model.ListaDeseos;
import com.duoc.stimy.gamehub.repository.ListaDeseosRepository;
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
public class ListaDeseosServiceTest {

    @InjectMocks
    private ListaDeseosService listaDeseosService;

    @Mock
    private ListaDeseosRepository listaDeseosRepository;

    @Mock
    private VideojuegoClient videojuegoClient;

    @Test
    public void testAgregarALista_Exito() {
        // ========== 1. ARRANGE ==========
        DeseoRequestDTO dto = new DeseoRequestDTO();
        dto.setUsuarioId(1L);
        dto.setVideojuegoId(303L);

        VideojuegoRemotoDTO juegoRemoto = new VideojuegoRemotoDTO();
        juegoRemoto.setTitulo("Cyberpunk 2077");

        when(videojuegoClient.obtenerVideojuegoPorId(303L)).thenReturn(juegoRemoto);

        ListaDeseos deseoGuardado = new ListaDeseos();
        deseoGuardado.setId(1L);
        deseoGuardado.setUsuarioId(1L);
        deseoGuardado.setVideojuegoId(303L);
        deseoGuardado.setFechaAgregado(LocalDate.now());

        when(listaDeseosRepository.save(any(ListaDeseos.class))).thenReturn(deseoGuardado);

        // ========== 2. ACT ==========
        // Llamamos al método real de tu servicio
        ListaDeseos resultado = listaDeseosService.agregarALista(dto);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals(303L, resultado.getVideojuegoId());
        verify(videojuegoClient, times(1)).obtenerVideojuegoPorId(303L);
        verify(listaDeseosRepository, times(1)).save(any(ListaDeseos.class));
    }

    @Test
    public void testAgregarALista_ErrorJuegoNoExiste() {
        // ========== 1. ARRANGE ==========
        DeseoRequestDTO dto = new DeseoRequestDTO();
        dto.setUsuarioId(1L);
        dto.setVideojuegoId(999L);

        when(videojuegoClient.obtenerVideojuegoPorId(999L)).thenReturn(null);

        // ========== 2. ACT & ASSERT ==========
        // Verificamos que lance la RuntimeException con el mensaje exacto de tu Service
        RuntimeException excepcion = assertThrows(RuntimeException.class, () ->
                listaDeseosService.agregarALista(dto)
        );

        assertEquals("Videojuego inexistente.", excepcion.getMessage());
        verify(listaDeseosRepository, never()).save(any(ListaDeseos.class));
    }

    @Test
    public void testObtenerPorUsuario() {
        // ========== 1. ARRANGE ==========
        ListaDeseos deseo = new ListaDeseos();
        deseo.setId(1L);
        deseo.setUsuarioId(1L);
        deseo.setVideojuegoId(303L);

        List<ListaDeseos> listaFalsa = new ArrayList<>();
        listaFalsa.add(deseo);

        when(listaDeseosRepository.findByUsuarioId(1L)).thenReturn(listaFalsa);

        // ========== 2. ACT ==========
        List<ListaDeseos> resultado = listaDeseosService.obtenerPorUsuario(1L);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(listaDeseosRepository, times(1)).findByUsuarioId(1L);
    }
}