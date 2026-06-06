package com.duoc.stimy.gamehub.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReseniaRequestDTO {

    @NotNull(message = "La calificación es obligatoria.")
    @Min(value = 1, message = "La calificación mínima es 1 estrella.")
    @Max(value = 5, message = "La calificación máxima es 5 estrellas.")
    private Integer calificacion;

    @NotBlank(message = "El comentario no puede estar vacío.")
    @Size(max = 500, message = "El comentario no puede exceder los 500 caracteres.")
    private String comentario;

    @NotNull(message = "El ID del videojuego es obligatorio.")
    private Long videojuegoId;

    @NotNull(message = "El ID del usuario es obligatorio.")
    private Long usuarioId;

    public Integer getCalificacion() { return calificacion; }
    public void setCalificacion(Integer calificacion) { this.calificacion = calificacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public Long getVideojuegoId() { return videojuegoId; }
    public void setVideojuegoId(Long videojuegoId) { this.videojuegoId = videojuegoId; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}