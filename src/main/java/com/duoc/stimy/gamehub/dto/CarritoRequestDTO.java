package com.duoc.stimy.gamehub.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CarritoRequestDTO {
    private Long id;
    private Long userId;
    private Long videojuegoId;
    private String nombreVideojuego;
    private Double precio;
}
