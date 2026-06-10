package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.VideojuegoClient;
import com.duoc.stimy.gamehub.dto.DeseoRequestDTO;
import com.duoc.stimy.gamehub.dto.VideojuegoRemotoDTO;
import com.duoc.stimy.gamehub.model.ListaDeseos;
import com.duoc.stimy.gamehub.repository.ListaDeseosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ListaDeseosService {
    private static final Logger log = LoggerFactory.getLogger(ListaDeseosService.class);

    @Autowired
    private ListaDeseosRepository listaDeseosRepository;

    @Autowired
    private VideojuegoClient videojuegoClient;

    public ListaDeseos agregarALista(DeseoRequestDTO dto) {
        log.info("[DESEO-SERVICE] Validando videojuego remoto ID: {}", dto.getVideojuegoId());
        VideojuegoRemotoDTO juego = videojuegoClient.obtenerVideojuegoPorId(dto.getVideojuegoId());

        if (juego == null) {
            log.error("[DESEO-SERVICE] El juego ID {} no existe.", dto.getVideojuegoId());
            throw new RuntimeException("Videojuego inexistente.");
        }

        ListaDeseos deseo = new ListaDeseos();
        deseo.setUsuarioId(dto.getUsuarioId());
        deseo.setVideojuegoId(dto.getVideojuegoId());
        deseo.setFechaAgregado(LocalDate.now());
        return listaDeseosRepository.save(deseo);
    }

    public List<ListaDeseos> obtenerPorUsuario(Long usuarioId) {
        log.info("[DESEO-SERVICE] Listando deseos de usuario ID: {}", usuarioId);
        return listaDeseosRepository.findByUsuarioId(usuarioId);
    }
}