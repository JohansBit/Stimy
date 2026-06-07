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
public class VideojuegoRequestDTO {

    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double precio;

    @NotBlank(message = "La desarrolladora es obligatoria")
    private String desarrolladora;

    @NotNull(message = "El año de salida es obligatorio")
    @Min(value = 1950, message = "El año de salida debe ser válido")
    private Integer anioSalida;

    @NotNull(message = "Debe indicar el ID de la categoría")
    private Long categoriaId;


}