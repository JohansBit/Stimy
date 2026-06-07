package com.duoc.stimy.gamehub.client;

import com.duoc.stimy.gamehub.dto.CarritoRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "carrito-client", url = "http://localhost:8083/api/carrito")
public interface CarritoClient {

    @GetMapping("/{usuarioId}")
    List<CarritoRequestDTO> obtenerCarrito(@PathVariable("usuarioId") Long usuarioId);

    @DeleteMapping("/{usuarioId}/vaciar")
    void vaciarCarrito(@PathVariable("usuarioId") Long usuarioId);
}
