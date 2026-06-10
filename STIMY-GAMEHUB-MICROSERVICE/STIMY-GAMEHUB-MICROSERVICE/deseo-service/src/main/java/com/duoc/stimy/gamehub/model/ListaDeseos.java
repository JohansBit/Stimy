package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "lista_deseos")
@Data
public class ListaDeseos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "videojuego_id", nullable = false)
    private Long videojuegoId;

    @Column(name = "fecha_agregado", nullable = false)
    private LocalDate fechaAgregado;
}