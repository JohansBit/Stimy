package com.duoc.stimy.gamehub.client;

import com.duoc.stimy.gamehub.model.Videojuego;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@FeignClient(name = "videojuego-client", url = "http://localhost:8080/api/videojuegos")
public interface VideojuegoClient {
    @GetMapping
    List<Videojuego> obtenerTodosLosVideojuegos();
}