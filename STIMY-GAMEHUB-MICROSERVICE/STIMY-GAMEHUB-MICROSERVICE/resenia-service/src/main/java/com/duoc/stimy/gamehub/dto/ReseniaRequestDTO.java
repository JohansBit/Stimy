package com.duoc.stimy.gamehub.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReseniaRequestDTO {

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1 estrella")
    @Max(value = 5, message = "La calificación máxima son 5 estrellas")
    private Integer calificacion;

    @NotBlank(message = "El comentario no puede estar vacío")
    @Size(max = 255, message = "El comentario no puede superar los 255 caracteres")
    private String comentario;

    @NotNull(message = "El ID del videojuego es obligatorio")
    private Long videojuegoId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;
}