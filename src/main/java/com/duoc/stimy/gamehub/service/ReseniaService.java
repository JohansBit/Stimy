package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.dto.ReseniaRequestDTO;
import com.duoc.stimy.gamehub.model.Resenia;
import com.duoc.stimy.gamehub.repository.ReseniaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReseniaService {

    private static final Logger log = LoggerFactory.getLogger(ReseniaService.class);

    @Autowired
    private ReseniaRepository reseniaRepository;

    public Resenia crearResenia(ReseniaRequestDTO dto) {
        log.info("Service: Iniciando creación de reseña para el videojuego ID: {}", dto.getVideojuegoId());
        try {
            Resenia nuevaResenia = new Resenia();
            nuevaResenia.setCalificacion(dto.getCalificacion());
            nuevaResenia.setComentario(dto.getComentario());
            nuevaResenia.setVideojuegoId(dto.getVideojuegoId());
            nuevaResenia.setUsuarioId(dto.getUsuarioId());

            Resenia reseniaGuardada = reseniaRepository.save(nuevaResenia);
            log.info("Service: Reseña creada exitosamente con ID: {}", reseniaGuardada.getId());
            return reseniaGuardada;
        } catch (Exception e) {
            log.error("Service: Error crítico al guardar la reseña del usuario {}: {}", dto.getUsuarioId(), e.getMessage());
            throw new RuntimeException("Error al procesar y guardar la reseña: " + e.getMessage());
        }
    }

    public List<Resenia> obtenerReseniasPorVideojuego(Long videojuegoId) {
        log.info("Service: Buscando reseñas para el videojuego ID: {}", videojuegoId);
        try {
            List<Resenia> resenias = reseniaRepository.findByVideojuegoId(videojuegoId);
            log.info("Service: Se encontraron {} reseñas para el videojuego ID: {}", resenias.size(), videojuegoId);
            return resenias;
        } catch (Exception e) {
            log.error("Service: Error al buscar las reseñas del videojuego ID {}: {}", videojuegoId, e.getMessage());
            throw new RuntimeException("Error al buscar las reseñas en la base de datos: " + e.getMessage());
        }
    }
}