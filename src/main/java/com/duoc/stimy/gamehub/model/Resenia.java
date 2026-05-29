package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resenias")
public class Resenia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer calificacion;

    @Column(nullable = false, length = 500)
    private String comentario;

    @Column(name = "fecha_publicacion", nullable = false)
    private LocalDate fechaPublicacion;

    @Column(name = "videojuego_id", nullable = false)
    private Long videojuegoId;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    public Resenia() {
        this.fechaPublicacion = LocalDate.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getCalificacion() { return calificacion; }
    public void setCalificacion(Integer calificacion) { this.calificacion = calificacion; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    public LocalDate getFechaPublicacion() { return fechaPublicacion; }
    public void setFechaPublicacion(LocalDate fechaPublicacion) { this.fechaPublicacion = fechaPublicacion; }

    public Long getVideojuegoId() { return videojuegoId; }
    public void setVideojuegoId(Long videojuegoId) { this.videojuegoId = videojuegoId; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}