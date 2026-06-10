package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.VideojuegoClient;
import com.duoc.stimy.gamehub.dto.CarritoRequestDTO;
import com.duoc.stimy.gamehub.dto.VideojuegoRemotoDTO;
import com.duoc.stimy.gamehub.model.ItemCarrito;
import com.duoc.stimy.gamehub.repository.ItemCarritoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class ItemCarritoService {
    private static final Logger log = LoggerFactory.getLogger(ItemCarritoService.class);

    @Autowired
    private ItemCarritoRepository itemCarritoRepository;

    @Autowired
    private VideojuegoClient videojuegoClient;

    public ItemCarrito agregarItem(CarritoRequestDTO dto) {
        log.info("[CARRITO-SERVICE] Solicitando verificación remota para Videojuego ID: {}", dto.getVideojuegoId());

        // Llamada remota síncrona vía OpenFeign
        VideojuegoRemotoDTO juegoRemoto = videojuegoClient.obtenerVideojuegoPorId(dto.getVideojuegoId());

        if (juegoRemoto == null) {
            log.error("[CARRITO-SERVICE] Error: El videojuego ID {} no existe en el catálogo remoto.", dto.getVideojuegoId());
            throw new RuntimeException("El videojuego solicitado no existe.");
        }

        log.info("[CARRITO-SERVICE] Videojuego verificado exitosamente: '{}'. Precio: {}", juegoRemoto.getTitulo(), juegoRemoto.getPrecio());

        ItemCarrito item = new ItemCarrito();
        item.setUsuarioId(dto.getUsuarioId());
        item.setVideojuegoId(dto.getVideojuegoId());
        item.setPrecio(juegoRemoto.getPrecio()); // Aseguramos consistencia de precio desde el catálogo

        return itemCarritoRepository.save(item);
    }

    public List<ItemCarrito> obtenerCarritoPorUsuario(Long usuarioId) {
        log.info("[CARRITO-SERVICE] Consultando ítems en base de datos para el Usuario ID: {}", usuarioId);
        return itemCarritoRepository.findByUsuarioId(usuarioId);
    }

    @Transactional
    public void vaciarCarrito(Long usuarioId) {
        log.info("[CARRITO-SERVICE] Eliminando todos los ítems del carrito para el Usuario ID: {}", usuarioId);
        itemCarritoRepository.deleteByUsuarioId(usuarioId);
    }
}