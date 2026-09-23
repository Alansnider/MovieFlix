package com.movieflix.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record MovieResponse(
        Long id,
        String name,
        String description,
        LocalDate releaseDate,
        Double rating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<CategoryResponse> categories,
        List<StreamingResponse> streamings) {
}
