package com.duoc.stimy.gamehub.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getDesarrolladora() { return desarrolladora; }
    public void setDesarrolladora(String desarrolladora) { this.desarrolladora = desarrolladora; }

    public Integer getAnioSalida() { return anioSalida; }
    public void setAnioSalida(Integer anioSalida) { this.anioSalida = anioSalida; }

    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
}