package com.duoc.stimy.gamehub.dto;

import jakarta.validation.constraints.NotNull;

public class DeseoRequestDTO {

    @NotNull(message = "El ID del usuario es obligatorio para agregar a la wishlist.")
    private Long usuarioId;

    @NotNull(message = "El ID del videojuego es obligatorio para agregarlo a la wishlist.")
    private Long videojuegoId;

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getVideojuegoId() { return videojuegoId; }
    public void setVideojuegoId(Long videojuegoId) { this.videojuegoId = videojuegoId; }
}