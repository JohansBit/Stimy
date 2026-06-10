package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.client.VideojuegoClient;
import com.duoc.stimy.gamehub.dto.ReseniaRequestDTO;
import com.duoc.stimy.gamehub.dto.VideojuegoRemotoDTO;
import com.duoc.stimy.gamehub.model.Resenia;
import com.duoc.stimy.gamehub.repository.ReseniaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReseniaService {
    private static final Logger log = LoggerFactory.getLogger(ReseniaService.class);

    @Autowired
    private ReseniaRepository reseniaRepository;

    @Autowired
    private VideojuegoClient videojuegoClient;

    public Resenia crearResenia(ReseniaRequestDTO dto) {
        log.info("[RESENIA-SERVICE] Verificando videojuego asociado id: {}", dto.getVideojuegoId());
        VideojuegoRemotoDTO juego = videojuegoClient.obtenerVideojuegoPorId(dto.getVideojuegoId());

        if (juego == null) {
            log.error("[RESENIA-SERVICE] Cancelando inserción: juego id {} no existe.", dto.getVideojuegoId());
            throw new RuntimeException("No se puede comentar un videojuego inexistente.");
        }

        Resenia resenia = new Resenia();
        resenia.setCalificacion(dto.getCalificacion());
        resenia.setComentario(dto.getComentario());
        resenia.setVideojuegoId(dto.getVideojuegoId());
        resenia.setUsuarioId(dto.getUsuarioId());
        resenia.setFechaPublicacion(LocalDate.now());

        return reseniaRepository.save(resenia);
    }

    public List<Resenia> obtenerPorVideojuego(Long videojuegoId) {
        log.info("[RESENIA-SERVICE] Trayendo reseñas para el juego ID: {}", videojuegoId);
        return reseniaRepository.findByVideojuegoId(videojuegoId);
    }
}