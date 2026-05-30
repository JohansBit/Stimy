package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.dto.LogroRequestDTO;
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

    public Logro crearLogro(LogroRequestDTO dto) {
        log.info("Service: Iniciando el proceso de creación del logro: {}", dto.getNombre());
        try {
            Logro nuevoLogro = new Logro();
            nuevoLogro.setNombre(dto.getNombre());
            nuevoLogro.setDescripcion(dto.getDescripcion());
            nuevoLogro.setPuntosRecompensa(dto.getPuntosRecompensa());
            nuevoLogro.setVideojuegoId(dto.getVideojuegoId());

            Logro logroGuardado = logroRepository.save(nuevoLogro);
            log.info("Service: Logro creado exitosamente en la BD con ID: {}", logroGuardado.getId());
            return logroGuardado;

        } catch (Exception e) {
            log.error("Service: Fallo crítico al intentar guardar el logro {}: {}", dto.getNombre(), e.getMessage());
            throw new RuntimeException("Error al crear el logro en la base de datos: " + e.getMessage());
        }
    }

    public List<Logro> obtenerLogrosPorVideojuego(Long videojuegoId) {
        log.info("Service: Solicitando búsqueda de logros para el videojuego ID: {}", videojuegoId);
        try {
            List<Logro> logros = logroRepository.findByVideojuegoId(videojuegoId);
            log.info("Service: Se encontraron {} logros para el videojuego ID: {}", logros.size(), videojuegoId);
            return logros;
        } catch (Exception e) {
            log.error("Service: Error al buscar logros del videojuego ID {}: {}", videojuegoId, e.getMessage());
            throw new RuntimeException("Error al buscar los logros: " + e.getMessage());
        }
    }
}