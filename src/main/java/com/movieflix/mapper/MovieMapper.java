package com.movieflix.mapper;

import com.movieflix.dto.request.MovieRequest;
import com.movieflix.dto.response.MovieResponse;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;

import java.util.List;

public final class MovieMapper {

    private MovieMapper() {
    }

    /**
     * Categorias e streamings são criados apenas com o id (referências);
     * o MovieService os resolve para as entidades gerenciadas.
     */
    public static Movie toMovie(MovieRequest request) {
        List<Category> categories = request.categories() == null ? List.of() :
                request.categories().stream()
                        .map(id -> Category.builder().id(id).build())
                        .toList();

        List<Streaming> streamings = request.streamings() == null ? List.of() :
                request.streamings().stream()
                        .map(id -> Streaming.builder().id(id).build())
                        .toList();

        return Movie.builder()
                .name(request.name())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toResponse(Movie movie) {
        return new MovieResponse(
                movie.getId(),
                movie.getName(),
                movie.getDescription(),
                movie.getReleaseDate(),
                movie.getRating(),
                movie.getCreatedAt(),
                movie.getUpdatedAt(),
                movie.getCategories().stream().map(CategoryMapper::toResponse).toList(),
                movie.getStreamings().stream().map(StreamingMapper::toResponse).toList());
    }
}
