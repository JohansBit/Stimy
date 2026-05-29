package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.dto.ReseniaRequestDTO;
import com.duoc.stimy.gamehub.model.Resenia;
import com.duoc.stimy.gamehub.repository.ReseniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReseniaService {

    @Autowired
    private ReseniaRepository reseniaRepository;

    public Resenia crearResenia(ReseniaRequestDTO dto) {
        try {
            Resenia nuevaResenia = new Resenia();
            nuevaResenia.setCalificacion(dto.getCalificacion());
            nuevaResenia.setComentario(dto.getComentario());
            nuevaResenia.setVideojuegoId(dto.getVideojuegoId());
            nuevaResenia.setUsuarioId(dto.getUsuarioId());

            return reseniaRepository.save(nuevaResenia);
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar y guardar la reseña: " + e.getMessage());
        }
    }

    public List<Resenia> obtenerReseniasPorVideojuego(Long videojuegoId) {
        try {
            return reseniaRepository.findByVideojuegoId(videojuegoId);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar las reseñas en la base de datos: " + e.getMessage());
        }
    }
}