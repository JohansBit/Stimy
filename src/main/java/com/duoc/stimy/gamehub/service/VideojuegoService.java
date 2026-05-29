package com.duoc.stimy.gamehub.service;

import com.duoc.stimy.gamehub.dto.VideojuegoRequestDTO;
import com.duoc.stimy.gamehub.model.Categoria;
import com.duoc.stimy.gamehub.model.Videojuego;
import com.duoc.stimy.gamehub.repository.CategoriaRepository;
import com.duoc.stimy.gamehub.repository.VideojuegoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideojuegoService {

    @Autowired
    private VideojuegoRepository videojuegoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Videojuego crearVideojuego(VideojuegoRequestDTO dto) {
        try {
            Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Error: La categoría indicada no existe en Stimy"));

            Videojuego nuevoJuego = new Videojuego();
            nuevoJuego.setTitulo(dto.getTitulo());
            nuevoJuego.setPrecio(dto.getPrecio());
            nuevoJuego.setDesarrolladora(dto.getDesarrolladora());
            nuevoJuego.setAnioSalida(dto.getAnioSalida());
            nuevoJuego.setCategoria(categoria);

            return videojuegoRepository.save(nuevoJuego);

        } catch (Exception e) {
            throw new RuntimeException("Hubo un problema al registrar el videojuego: " + e.getMessage());
        }
    }

    public List<Videojuego> obtenerTodos() {
        return videojuegoRepository.findAll();
    }
}