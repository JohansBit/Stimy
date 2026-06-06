package com.duoc.stimy.gamehub.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AmigoRequestDTO {

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long usuarioId;

    @NotNull(message = "El ID del amigo es obligatorio")
    private Long amigoId;
}