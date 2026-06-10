package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.model.Videojuego;
import com.duoc.stimy.gamehub.repository.VideojuegoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VideojuegoService {
    private static final Logger log = LoggerFactory.getLogger(VideojuegoService.class);

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    public Videojuego guardarVideojuego(Videojuego juego) {
        log.info("[VIDEOJUEGO-SERVICE] Guardando juego en catalogo: {}", juego.getTitulo());
        return videojuegoRepository.save(juego);
    }

    public List<Videojuego> listarTodos() {
        log.info("[VIDEOJUEGO-SERVICE] Buscando catalogo completo de videojuegos");
        return videojuegoRepository.findAll();
    }

    public Videojuego buscarPorId(Long id) {
        log.info("[VIDEOJUEGO-SERVICE] Buscando videojuego id: {}", id);
        return videojuegoRepository.findById(id).orElse(null);
    }
}