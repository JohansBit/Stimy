package com.duoc.stimy.gamehub.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "Pagos")
public class Pagos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPago;

    @NotNull(message = "El ID usuario no puede ser nulo")
    @Column(name = "usuarioId", unique = true)
    private Long usuarioId;

    @NotNull(message = "El monto total no puede ser nulo")
    @Min(message = "El monto total no puede ser negativo", value = 0)
    @Column(name = "montoTotal")
    private int montoTotal;

    @NotNull(message = "El estado no puede ser nulo")
    @Column(name = "estado", length = 50)
    private String estado;

    @NotNull(message = "La fecha de pago no puede ser nula")
    @Column(name = "fechaPago")
    private LocalDateTime fechaPago;
}
