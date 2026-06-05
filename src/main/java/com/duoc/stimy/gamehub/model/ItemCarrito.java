package com.duoc.stimy.gamehub.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Table(name = "items_carrito")

public class ItemCarrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "EL ID de Usuario es obligatorio")
    @Column(name = "usuarioId", nullable = false)
    private Long usuarioId;

    @NotNull(message = "El ID del juego es obligatorio")
    @Column(name = "videojuego_id", nullable = false)
    private Long juegoId;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    @Column(name = "precio", nullable = false)
    private Double precio;




