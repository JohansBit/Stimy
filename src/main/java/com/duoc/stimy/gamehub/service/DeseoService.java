package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.dto.DeseoRequestDTO;
import com.duoc.stimy.gamehub.model.Deseo;
import com.duoc.stimy.gamehub.repository.DeseoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeseoService {

    private static final Logger log = LoggerFactory.getLogger(DeseoService.class);

    @Autowired
    private DeseoRepository deseoRepository;

    public Deseo agregarDeseo(DeseoRequestDTO dto) {
        log.info("Service: Iniciando proceso para agregar el videojuego ID {} a la wishlist del usuario ID {}", dto.getVideojuegoId(), dto.getUsuarioId());
        try {
            Deseo nuevoDeseo = new Deseo();
            nuevoDeseo.setUsuarioId(dto.getUsuarioId());
            nuevoDeseo.setVideojuegoId(dto.getVideojuegoId());

            Deseo deseoGuardado = deseoRepository.save(nuevoDeseo);
            log.info("Service: Videojuego agregado exitosamente a la wishlist. ID del registro: {}", deseoGuardado.getId());
            return deseoGuardado;
        } catch (Exception e) {
            log.error("Service: Error al intentar guardar en la wishlist del usuario {}: {}", dto.getUsuarioId(), e.getMessage());
            throw new RuntimeException("Error al agregar a la lista de deseos: " + e.getMessage());
        }
    }

    public List<Deseo> obtenerDeseosPorUsuario(Long usuarioId) {
        log.info("Service: Consultando la wishlist del usuario ID: {}", usuarioId);
        try {
            List<Deseo> listaDeseos = deseoRepository.findByUsuarioId(usuarioId);
            log.info("Service: Se encontraron {} juegos en la wishlist del usuario ID: {}", listaDeseos.size(), usuarioId);
            return listaDeseos;
        } catch (Exception e) {
            log.error("Service: Error al buscar la wishlist del usuario ID {}: {}", usuarioId, e.getMessage());
            throw new RuntimeException("Error al consultar la lista de deseos: " + e.getMessage());
        }
    }
}