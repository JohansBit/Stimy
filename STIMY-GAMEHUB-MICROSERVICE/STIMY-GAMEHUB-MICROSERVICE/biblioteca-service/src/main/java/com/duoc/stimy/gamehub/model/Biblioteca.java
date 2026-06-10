package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "bibliotecas")
@Data
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "videojuego_id", nullable = false)
    private Long videojuegoId;

    @Column(name = "horas_jugadas", nullable = false)
    private Double horasJugadas;

    @Column(name = "fecha_adquisicion", nullable = false)
    private LocalDate fechaAdquisicion;
}