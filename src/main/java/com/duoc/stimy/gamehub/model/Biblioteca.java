package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "biblioteca")
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBiblioteca;

    @NotNull(message = "El ID usuario no puede ser nulo")
    @Column(name = "usuarioId")
    private Long usuarioId;

    @NotNull(message = "El ID del videojuego no puede ser nulo")
    @Column(name = "videojuegoId")
    private Long videojuegoId;

    @NotNull(message = "La fecha de compra no puede ser nula")
    @Column(name = "fechaCompra")
    private LocalDateTime fechaCompra;


}
