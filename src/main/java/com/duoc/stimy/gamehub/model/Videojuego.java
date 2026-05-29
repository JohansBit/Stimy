package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "videojuegos")
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Column(nullable = false)
    private String titulo;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    @Column(nullable = false)
    private Double precio;

    @NotBlank(message = "La desarrolladora es obligatoria")
    @Column(nullable = false)
    private String desarrolladora;

    @NotNull(message = "El año de salida es obligatorio")
    @Min(value = 1950, message = "El año de salida debe ser válido")
    @Column(name = "anio_salida", nullable = false)
    private Integer anioSalida;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Videojuego() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getDesarrolladora() { return desarrolladora; }
    public void setDesarrolladora(String desarrolladora) { this.desarrolladora = desarrolladora; }

    public Integer getAnioSalida() { return anioSalida; }
    public void setAnioSalida(Integer anioSalida) { this.anioSalida = anioSalida; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}