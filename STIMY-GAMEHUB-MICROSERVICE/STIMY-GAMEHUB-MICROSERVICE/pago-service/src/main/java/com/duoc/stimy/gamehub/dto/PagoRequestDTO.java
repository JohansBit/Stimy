package com.duoc.stimy.gamehub.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagoRequestDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El monto de la transacción es obligatorio")
    @Min(value = 1, message = "El monto mínimo a pagar debe ser de 1")
    private Double monto;

    @NotBlank(message = "El método de pago es obligatorio (TARJETA, WEBPAY, PAYPAL)")
    private String metodoPago;
}