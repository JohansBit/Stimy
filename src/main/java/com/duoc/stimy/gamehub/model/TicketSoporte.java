package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets_soporte")
@Data
public class TicketSoporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private com.duoc.stimy.base_service.model.Usuario usuario;

    @Column(nullable = false)
    private String asunto;

    @Column(nullable = false, length = 1000)
    private String descripcion;


    @Column(nullable = false)
    private String estado;

    private LocalDateTime fechaCreacion;


    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
        if (this.estado == null) {
            this.estado = "ABIERTO"; // Estado por defecto
        }
    }
}