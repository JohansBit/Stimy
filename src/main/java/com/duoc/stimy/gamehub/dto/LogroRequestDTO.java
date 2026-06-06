package com.duoc.stimy.gamehub.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LogroRequestDTO {

    @NotBlank(message = "El nombre del logro no puede estar vacío.")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres.")
    private String nombre;

    @NotBlank(message = "La descripción del logro es obligatoria.")
    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres.")
    private String descripcion;

    @NotNull(message = "Los puntos de recompensa son obligatorios.")
    @Min(value = 5, message = "Un logro debe dar al menos 5 puntos.")
    @Max(value = 1000, message = "Ningún logro puede otorgar más de 1000 puntos.")
    private Integer puntosRecompensa;

    @NotNull(message = "El ID del videojuego es obligatorio.")
    private Long videojuegoId;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getPuntosRecompensa() { return puntosRecompensa; }
    public void setPuntosRecompensa(Integer puntosRecompensa) { this.puntosRecompensa = puntosRecompensa; }

    public Long getVideojuegoId() { return videojuegoId; }
    public void setVideojuegoId(Long videojuegoId) { this.videojuegoId = videojuegoId; }
}