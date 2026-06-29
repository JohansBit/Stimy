package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.VideojuegoClient;
import com.duoc.stimy.gamehub.dto.CarritoRequestDTO;
import com.duoc.stimy.gamehub.dto.VideojuegoRemotoDTO;
import com.duoc.stimy.gamehub.model.ItemCarrito;
import com.duoc.stimy.gamehub.repository.ItemCarritoRepository;
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
public class ItemCarritoServiceTest {

    @InjectMocks
    private ItemCarritoService itemCarritoService;

    @Mock
    private ItemCarritoRepository itemCarritoRepository;

    @Mock
    private VideojuegoClient videojuegoClient;

    @Test
    public void testAgregarItem_Exito() {
        // ========== 1. ARRANGE ==========
        CarritoRequestDTO dto = new CarritoRequestDTO();
        dto.setUsuarioId(1L);
        dto.setVideojuegoId(202L);

        VideojuegoRemotoDTO juegoRemoto = new VideojuegoRemotoDTO();
        juegoRemoto.setTitulo("Elden Ring");
        juegoRemoto.setPrecio(59.99);

        when(videojuegoClient.obtenerVideojuegoPorId(202L)).thenReturn(juegoRemoto);

        ItemCarrito itemGuardado = new ItemCarrito();
        itemGuardado.setId(1L);
        itemGuardado.setUsuarioId(1L);
        itemGuardado.setVideojuegoId(202L);
        itemGuardado.setPrecio(59.99);

        when(itemCarritoRepository.save(any(ItemCarrito.class))).thenReturn(itemGuardado);

        // ========== 2. ACT ==========
        ItemCarrito resultado = itemCarritoService.agregarItem(dto);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals(202L, resultado.getVideojuegoId());
        assertEquals(59.99, resultado.getPrecio());

        verify(videojuegoClient, times(1)).obtenerVideojuegoPorId(202L);
        verify(itemCarritoRepository, times(1)).save(any(ItemCarrito.class));
    }

    @Test
    public void testAgregarItem_ErrorJuegoNoExiste() {
        // ========== 1. ARRANGE ==========
        CarritoRequestDTO dto = new CarritoRequestDTO();
        dto.setUsuarioId(1L);
        dto.setVideojuegoId(999L);

        when(videojuegoClient.obtenerVideojuegoPorId(999L)).thenReturn(null);

        // ========== 2. ACT & ASSERT ==========
        RuntimeException excepcion = assertThrows(RuntimeException.class, () ->
                itemCarritoService.agregarItem(dto)
        );

        assertEquals("El videojuego solicitado no existe.", excepcion.getMessage());
        verify(itemCarritoRepository, never()).save(any(ItemCarrito.class));
    }

    @Test
    public void testObtenerCarritoPorUsuario() {
        // ========== 1. ARRANGE ==========
        ItemCarrito item = new ItemCarrito();
        item.setId(1L);
        item.setUsuarioId(1L);
        item.setVideojuegoId(202L);

        List<ItemCarrito> listaFalsa = new ArrayList<>();
        listaFalsa.add(item);

        when(itemCarritoRepository.findByUsuarioId(1L)).thenReturn(listaFalsa);

        // ========== 2. ACT ==========
        List<ItemCarrito> resultado = itemCarritoService.obtenerCarritoPorUsuario(1L);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(itemCarritoRepository, times(1)).findByUsuarioId(1L);
    }

    @Test
    public void testVaciarCarrito() {
        // ========== 1. ARRANGE ==========
        Long usuarioId = 1L;
        doNothing().when(itemCarritoRepository).deleteByUsuarioId(usuarioId);

        // ========== 2. ACT & ASSERT ==========
        assertDoesNotThrow(() -> itemCarritoService.vaciarCarrito(usuarioId));
        verify(itemCarritoRepository, times(1)).deleteByUsuarioId(usuarioId);
    }
}