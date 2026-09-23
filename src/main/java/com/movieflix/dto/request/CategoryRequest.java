package com.movieflix.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryRequest(
        @NotBlank(message = "Nome da categoria é obrigatório")
        @Size(max = 100, message = "Nome da categoria deve ter no máximo 100 caracteres")
        String name) {
}
