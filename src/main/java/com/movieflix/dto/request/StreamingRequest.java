package com.movieflix.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record StreamingRequest(
        @NotBlank(message = "Nome do streaming é obrigatório")
        @Size(max = 100, message = "Nome do streaming deve ter no máximo 100 caracteres")
        String name) {
}
