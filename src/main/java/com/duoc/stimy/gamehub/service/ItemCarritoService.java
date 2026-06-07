package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.VideojuegoClient;
import com.duoc.stimy.gamehub.dto.VideojuegoRequestDTO;
import com.duoc.stimy.gamehub.dto.CarritoRequestDTO;
import com.duoc.stimy.gamehub.model.ItemCarrito;
import com.duoc.stimy.gamehub.repository.ItemCarritoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemCarritoService {

    private static final Logger log = LoggerFactory.getLogger(ItemCarritoService.class);

    private final ItemCarritoRepository itemCarritoRepository;
    private final VideojuegoClient videojuegoClient;

    public ItemCarritoService(ItemCarritoRepository itemCarritoRepository, VideojuegoClient videojuegoClient) {
        this.itemCarritoRepository = itemCarritoRepository;
        this.videojuegoClient = videojuegoClient;
    }

    /* Metodo para agregar juegos al carrito */
    public ItemCarrito agregarAlCarrito(ItemCarrito item){
        log.info("Service: Agregando juego Id: {} al carrito del usuario Id {}", item.getJuegoId(), item.getUsuarioId());
        ItemCarrito guardado = itemCarritoRepository.save(item);
        log.info("Service: Juego agregado exitosamente con el Id registro {}", guardado.getJuegoId());
        return guardado;
    }

    /*Metodo para mostrar el carrito */
    public List<CarritoRequestDTO> obtenerCarritoPorUsuario(Long usuarioId) {
        log.info("Service: Consultando items en BDD para el usuario ID: {}", usuarioId);
        List<ItemCarrito> items = itemCarritoRepository.findByUsuarioId((usuarioId));
        log.info("Service: Se encontraron {} items locales. Iniciando cruce de datos", items.size());

        return items.stream().map(item -> {
            String nombreVideojuego;

            try {
                log.info("Feign: Enviando juegoId ({}) al cliente remoto en el puerto", item.getJuegoId());
                VideojuegoRequestDTO videojuegoExterno = videojuegoClient.obtenerVideojuegoPorId(item.getJuegoId());

                if (videojuegoExterno != null) {
                     nombreVideojuego = videojuegoExterno.getTitulo();
                    log.info("Feign: Videojuego encontrado con éxito! : {}", nombreVideojuego);
                } else {
                    nombreVideojuego = "Videojuego no encontrado";
                    log.warn("Feign: El microservicio respondio vacío para el videojuego ID: {}", item.getJuegoId());
                }
            } catch (Exception e) {
                nombreVideojuego = "Error al conectar con el servicio de Videojuegos";
                log.error("Feign Error: No se pudo comunicar con el servicio de Videojuegos. Error: {}", e.getMessage());
            }

            return new CarritoRequestDTO(
                    item.getId(),
                    item.getUsuarioId(),
                    item.getJuegoId(),
                    nombreVideojuego,
                    item.getPrecio()
            );

        }).collect(Collectors.toList());
    }

    @Transactional
    public void vaciarCarrito(Long usuarioId){
        log.warn("Service: Solicitud de vaciado completo de carrito para el usuario: {}", usuarioId);
        itemCarritoRepository.deleteByUsuarioId(usuarioId);
        log.info("Service: Carrito vaciado correctamente en la base de datos");
    }

}
