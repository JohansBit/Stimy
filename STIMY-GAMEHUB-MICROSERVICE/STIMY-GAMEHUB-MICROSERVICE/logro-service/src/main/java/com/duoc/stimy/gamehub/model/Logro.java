package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "logros")
@Data
public class Logro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(name = "puntos_recompensa", nullable = false)
    private Integer puntosRecompensa;

    @Column(name = "videojuego_id", nullable = false)
    private Long videojuegoId;
}