package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "items_carrito")
@Data
public class ItemCarrito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "videojuego_id", nullable = false)
    private Long videojuegoId;

    @Column(nullable = false)
    private Double precio;
}