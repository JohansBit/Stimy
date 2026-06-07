package com.duoc.stimy.gamehub.client;

import com.duoc.stimy.gamehub.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

// Con esta anotación le decimos a Feign que apunte al servicio de usuarios en la red local
@FeignClient(name = "usuario-service", url = "http://localhost:8081/api/usuarios")
public interface UsuarioClient {

    // Este método va a golpear el endpoint GET /api/usuarios/listar del otro microservicio
    @GetMapping("/listar")
    List<Usuario> obtenerTodosLosUsuarios();
}