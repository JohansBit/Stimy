package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "videojuegos")
@Data
public class Videojuego {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false)
    private Double precio;

    @Column(length = 100)
    private String desarrolladora;

    @Column(name = "anio_salida")
    private Integer anioSalida;
}