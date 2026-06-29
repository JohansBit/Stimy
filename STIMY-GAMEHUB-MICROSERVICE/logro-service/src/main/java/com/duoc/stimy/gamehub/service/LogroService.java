package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.VideojuegoClient;
import com.duoc.stimy.gamehub.dto.LogroRequestDTO;
import com.duoc.stimy.gamehub.dto.VideojuegoRemotoDTO;
import com.duoc.stimy.gamehub.model.Logro;
import com.duoc.stimy.gamehub.repository.LogroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LogroService {
    private static final Logger log = LoggerFactory.getLogger(LogroService.class);

    @Autowired
    private LogroRepository logroRepository;

    @Autowired
    private VideojuegoClient videojuegoClient;

    public Logro crearLogro(LogroRequestDTO dto) {
        log.info("[LOGRO-SERVICE] Validando existencia de videojuego id: {}", dto.getVideojuegoId());
        VideojuegoRemotoDTO juego = videojuegoClient.obtenerVideojuegoPorId(dto.getVideojuegoId());

        if (juego == null) {
            log.error("[LOGRO-SERVICE] El juego remoto ID {} no existe.", dto.getVideojuegoId());
            throw new RuntimeException("El videojuego para este logro no existe.");
        }

        Logro logro = new Logro();
        logro.setNombre(dto.getNombre());
        logro.setDescripcion(dto.getDescripcion());
        logro.setPuntosRecompensa(dto.getPuntosRecompensa());
        logro.setVideojuegoId(dto.getVideojuegoId());
        return logroRepository.save(logro);
    }

    public List<Logro> obtenerPorVideojuego(Long videojuegoId) {
        log.info("[LOGRO-SERVICE] Buscando logros asociados al juego ID: {}", videojuegoId);
        return logroRepository.findByVideojuegoId(videojuegoId);
    }
}