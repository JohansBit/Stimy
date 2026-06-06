package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "amigos")
@Data
public class Amigos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private Long amigoId;
    private String estado;
}