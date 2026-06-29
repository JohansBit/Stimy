package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.UsuarioClient;
import com.duoc.stimy.gamehub.dto.PagoRequestDTO;
import com.duoc.stimy.gamehub.dto.UsuarioRemotoDTO;
import com.duoc.stimy.gamehub.model.Transaccion;
import com.duoc.stimy.gamehub.repository.TransaccionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransaccionService {
    private static final Logger log = LoggerFactory.getLogger(TransaccionService.class);

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private UsuarioClient usuarioClient;

    public Transaccion procesarPago(PagoRequestDTO dto) {
        log.info("[PAGO-SERVICE] Validando cuenta de usuario remota ID: {}", dto.getUsuarioId());

        UsuarioRemotoDTO usuario = usuarioClient.obtenerUsuarioPorId(dto.getUsuarioId());

        if (usuario == null) {
            log.error("[PAGO-SERVICE] Error fatal: El usuario ID {} no existe.", dto.getUsuarioId());
            throw new RuntimeException("No se puede procesar el pago para un usuario inexistente.");
        }

        Transaccion transaccion = new Transaccion();
        transaccion.setUsuarioId(dto.getUsuarioId());
        transaccion.setMonto(dto.getMonto());
        transaccion.setMetodoPago(dto.getMetodoPago().toUpperCase());
        transaccion.setEstado("APROBADO"); // Simulamos aprobación inmediata de la pasarela
        transaccion.setFechaTransaccion(LocalDate.now());

        log.info("[PAGO-SERVICE] Transacción aprobada con éxito para el usuario: {}", usuario.getNombreUsuario());
        return transaccionRepository.save(transaccion);
    }

    public List<Transaccion> obtenerHistorialPorUsuario(Long usuarioId) {
        log.info("[PAGO-SERVICE] Buscando historial de pagos para usuario ID: {}", usuarioId);
        return transaccionRepository.findByUsuarioId(usuarioId);
    }
}