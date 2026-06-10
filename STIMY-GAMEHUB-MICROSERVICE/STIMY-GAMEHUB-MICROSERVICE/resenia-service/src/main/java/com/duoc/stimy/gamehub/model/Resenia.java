package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "resenias")
@Data
public class Resenia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer calificacion;

    @Column(nullable = false, length = 255)
    private String comentario;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;

    @Column(name = "videojuego_id", nullable = false)
    private Long videojuegoId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;
}