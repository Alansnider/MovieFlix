package com.movieflix.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record MovieRequest(
        @NotBlank(message = "Nome do filme é obrigatório")
        @Size(max = 255, message = "Nome do filme deve ter no máximo 255 caracteres")
        String name,

        String description,

        LocalDate releaseDate,

        @DecimalMin(value = "0.0", message = "Nota mínima é 0")
        @DecimalMax(value = "10.0", message = "Nota máxima é 10")
        Double rating,

        List<Long> categories,

        List<Long> streamings) {
}
