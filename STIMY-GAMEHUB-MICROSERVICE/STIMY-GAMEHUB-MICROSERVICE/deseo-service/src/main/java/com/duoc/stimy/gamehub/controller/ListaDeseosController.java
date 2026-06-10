package com.duoc.stimy.gamehub.controller;

import com.duoc.stimy.gamehub.dto.DeseoRequestDTO;
import com.duoc.stimy.gamehub.model.ListaDeseos;
import com.duoc.stimy.gamehub.service.ListaDeseosService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/deseos")
public class ListaDeseosController {
    private static final Logger log = LoggerFactory.getLogger(ListaDeseosController.class);

    @Autowired
    private ListaDeseosService listaDeseosService;

    @PostMapping("/agregar")
    public ResponseEntity<ListaDeseos> agregar(@Valid @RequestBody DeseoRequestDTO dto) {
        log.info("[DESEO-CONTROLLER] Solicitud POST agregar lista de deseos");
        ListaDeseos nuevo = listaDeseosService.agregarALista(dto);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<ListaDeseos>> obtener(@PathVariable Long usuarioId) {
        log.info("[DESEO-CONTROLLER] Solicitud GET deseos del usuario ID: {}", usuarioId);
        List<ListaDeseos> lista = listaDeseosService.obtenerPorUsuario(usuarioId);
        return ResponseEntity.ok(lista);
    }
}