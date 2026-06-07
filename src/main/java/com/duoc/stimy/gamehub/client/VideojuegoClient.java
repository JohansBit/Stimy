package com.duoc.stimy.gamehub.client;

import com.duoc.stimy.gamehub.dto.VideojuegoRequestDTO;
import com.duoc.stimy.gamehub.model.Videojuego;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "videojuego-client", url = "http://localhost:8082/api/videojuegos")
public interface VideojuegoClient {
    @GetMapping
    List<Videojuego> obtenerTodosLosVideojuegos();

    @GetMapping("/{id}")
    VideojuegoRequestDTO obtenerVideojuegoPorId(@PathVariable Long id);
}