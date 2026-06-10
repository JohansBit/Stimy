package com.duoc.stimy.gamehub.client;

import com.duoc.stimy.gamehub.dto.VideojuegoRemotoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "videojuego-service", url = "http://localhost:8082/api/videojuegos")
public interface VideojuegoClient {

    @GetMapping("/{id}")
    VideojuegoRemotoDTO obtenerVideojuegoPorId(@PathVariable("id") Long id);
}