package com.duoc.stimy.gamehub.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogroRequestDTO {

    @NotBlank(message = "El nombre del logro es obligatorio")
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "Los puntos de recompensa son obligatorios")
    @Min(value = 1, message = "Debe otorgar al menos 1 punto")
    private Integer puntosRecompensa;

    @NotNull(message = "El ID del videojuego asociado es obligatorio")
    private Long videojuegoId;
}