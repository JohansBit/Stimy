package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.dto.VideojuegoRequestDTO;
import com.duoc.stimy.gamehub.model.Categoria;
import com.duoc.stimy.gamehub.model.Videojuego;
import com.duoc.stimy.gamehub.repository.CategoriaRepository;
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

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Videojuego crearVideojuego(VideojuegoRequestDTO dto) {
        log.info("Service: Iniciando validación y creación del videojuego: {}", dto.getTitulo());
        try {
            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("La categoría no existe"));

            Videojuego nuevoJuego = new Videojuego();
            nuevoJuego.setTitulo(dto.getTitulo());
            nuevoJuego.setPrecio(dto.getPrecio());
            nuevoJuego.setDesarrolladora(dto.getDesarrolladora());
            nuevoJuego.setAnioSalida(dto.getAnioSalida());
            nuevoJuego.setCategoria(categoria);

            Videojuego guardado = videojuegoRepository.save(nuevoJuego);
            log.info("Service: Videojuego guardado exitosamente en BD con ID: {}", guardado.getId());
            return guardado;
        } catch (Exception e) {
            log.error("Service: Error al crear el videojuego {}: {}", dto.getTitulo(), e.getMessage());
            throw new RuntimeException("Error al guardar el videojuego: " + e.getMessage());
        }
    }

    public List<Videojuego> obtenerTodos() {
        log.info("Service: Consultando la base de datos para obtener todos los videojuegos");
        try {
            List<Videojuego> juegos = videojuegoRepository.findAll();
            log.info("Service: Se encontraron {} videojuegos", juegos.size());
            return juegos;
        } catch (Exception e) {
            log.error("Service: Error al consultar los videojuegos: {}", e.getMessage());
            throw new RuntimeException("Error al consultar la base de datos: " + e.getMessage());
        }
    }
}