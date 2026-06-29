package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.UsuarioClient;
import com.duoc.stimy.gamehub.dto.PagoRequestDTO;
import com.duoc.stimy.gamehub.dto.UsuarioRemotoDTO;
import com.duoc.stimy.gamehub.model.Transaccion;
import com.duoc.stimy.gamehub.repository.TransaccionRepository;
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
public class TransaccionServiceTest {

    @InjectMocks
    private TransaccionService transaccionService;

    @Mock
    private TransaccionRepository transaccionRepository;

    @Mock
    private UsuarioClient usuarioClient;

    @Test
    public void testProcesarPago_Exito() {
        // ========== 1. ARRANGE ==========
        PagoRequestDTO dto = new PagoRequestDTO();
        dto.setUsuarioId(5L);
        dto.setMonto(59.99);
        dto.setMetodoPago("credit_card");

        UsuarioRemotoDTO usuarioRemoto = new UsuarioRemotoDTO();
        usuarioRemoto.setNombreUsuario("GamerPro99");

        when(usuarioClient.obtenerUsuarioPorId(5L)).thenReturn(usuarioRemoto);

        Transaccion transaccionEsperada = new Transaccion();
        transaccionEsperada.setId(100L);
        transaccionEsperada.setUsuarioId(5L);
        transaccionEsperada.setMonto(59.99);
        transaccionEsperada.setMetodoPago("CREDIT_CARD"); // Valida transformación toUpperCase()
        transaccionEsperada.setEstado("APROBADO");
        transaccionEsperada.setFechaTransaccion(LocalDate.now());

        when(transaccionRepository.save(any(Transaccion.class))).thenReturn(transaccionEsperada);

        // ========== 2. ACT ==========
        Transaccion resultado = transaccionService.procesarPago(dto);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals(100L, resultado.getId());
        assertEquals("APROBADO", resultado.getEstado());
        assertEquals("CREDIT_CARD", resultado.getMetodoPago());

        verify(usuarioClient, times(1)).obtenerUsuarioPorId(5L);
        verify(transaccionRepository, times(1)).save(any(Transaccion.class));
    }

    @Test
    public void testProcesarPago_ErrorUsuarioNoExiste() {
        // ========== 1. ARRANGE ==========
        PagoRequestDTO dto = new PagoRequestDTO();
        dto.setUsuarioId(999L);
        dto.setMonto(19.99);
        dto.setMetodoPago("paypal");

        when(usuarioClient.obtenerUsuarioPorId(999L)).thenReturn(null);

        // ========== 2. ACT & ASSERT ==========
        RuntimeException excepcion = assertThrows(RuntimeException.class, () ->
                transaccionService.procesarPago(dto)
        );

        assertEquals("No se puede procesar el pago para un usuario inexistente.", excepcion.getMessage());
        verify(transaccionRepository, never()).save(any(Transaccion.class));
    }

    @Test
    public void testObtenerHistorialPorUsuario() {
        // ========== 1. ARRANGE ==========
        Transaccion t1 = new Transaccion();
        t1.setId(101L);
        t1.setUsuarioId(5L);
        t1.setMonto(9.99);

        List<Transaccion> historialFalso = new ArrayList<>();
        historialFalso.add(t1);

        when(transaccionRepository.findByUsuarioId(5L)).thenReturn(historialFalso);

        // ========== 2. ACT ==========
        List<Transaccion> resultado = transaccionService.obtenerHistorialPorUsuario(5L);

        // ========== 3. ASSERT ==========
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(101L, resultado.get(0).getId());
        verify(transaccionRepository, times(1)).findByUsuarioId(5L);
    }
}