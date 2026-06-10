package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.VideojuegoClient;
import com.duoc.stimy.gamehub.dto.BibliotecaRequestDTO;
import com.duoc.stimy.gamehub.dto.VideojuegoRemotoDTO;
import com.duoc.stimy.gamehub.model.Biblioteca;
import com.duoc.stimy.gamehub.repository.BibliotecaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BibliotecaService {
    private static final Logger log = LoggerFactory.getLogger(BibliotecaService.class);

    @Autowired
    private BibliotecaRepository bibliotecaRepository;

    @Autowired
    private VideojuegoClient videojuegoClient;

    public Biblioteca agregarJuegoABiblioteca(BibliotecaRequestDTO dto) {
        log.info("[BIBLIOTECA-SERVICE] Comprobando existencia de videojuego en catalogo ID: {}", dto.getVideojuegoId());

        VideojuegoRemotoDTO juego = videojuegoClient.obtenerVideojuegoPorId(dto.getVideojuegoId());

        if (juego == null) {
            log.error("[BIBLIOTECA-SERVICE] Error: El juego ID {} no existe.", dto.getVideojuegoId());
            throw new RuntimeException("No se puede añadir un juego inexistente a la biblioteca.");
        }

        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setUsuarioId(dto.getUsuarioId());
        biblioteca.setVideojuegoId(dto.getVideojuegoId());
        biblioteca.setHorasJugadas(0.0);
        biblioteca.setFechaAdquisicion(LocalDate.now());

        log.info("[BIBLIOTECA-SERVICE] Juego '{}' añadido con éxito al usuario ID: {}", juego.getTitulo(), dto.getUsuarioId());
        return bibliotecaRepository.save(biblioteca);
    }

    public List<Biblioteca> obtenerColeccionUsuario(Long usuarioId) {
        log.info("[BIBLIOTECA-SERVICE] Cargando biblioteca del usuario ID: {}", usuarioId);
        return bibliotecaRepository.findByUsuarioId(usuarioId);
    }
}