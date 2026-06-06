package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lista_deseos")
public class Deseo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "videojuego_id", nullable = false)
    private Long videojuegoId;

    @Column(name = "fecha_agregado", nullable = false)
    private LocalDate fechaAgregado;

    public Deseo() {
        this.fechaAgregado = LocalDate.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public Long getVideojuegoId() { return videojuegoId; }
    public void setVideojuegoId(Long videojuegoId) { this.videojuegoId = videojuegoId; }

    public LocalDate getFechaAgregado() { return fechaAgregado; }
    public void setFechaAgregado(LocalDate fechaAgregado) { this.fechaAgregado = fechaAgregado; }
}