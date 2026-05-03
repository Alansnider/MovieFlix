package com.br.MovieFlix.MovieFlix.mapper;

import com.br.MovieFlix.MovieFlix.controller.request.MovieRequest;
import com.br.MovieFlix.MovieFlix.controller.response.MovieResponse;
import com.br.MovieFlix.MovieFlix.entity.Category;
import com.br.MovieFlix.MovieFlix.entity.Movie;
import com.br.MovieFlix.MovieFlix.entity.Streaming;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MovieMapper {

    private final CategoryMapper categoryMapper;
    private final StreamingMapper streamingMapper;

    public MovieMapper(CategoryMapper categoryMapper,
                       StreamingMapper streamingMapper) {
        this.categoryMapper = categoryMapper;
        this.streamingMapper = streamingMapper;
    }

    public Movie toEntity(MovieRequest request) {

        Set<Category> categories = request.categories().stream()
                .map(id -> Category.builder().id(id).build())
                .collect(Collectors.toSet());

        Set<Streaming> streamings = request.streamings().stream()
                .map(id -> Streaming.builder().id(id).build())
                .collect(Collectors.toSet());

        return Movie.builder()
                .title(request.title())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .rating(request.rating())
                .categories(categories)
                .streamings(streamings)
                .build();
    }

    public MovieResponse toResponse(Movie movie) {

        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .releaseDate(movie.getReleaseDate())
                .rating(movie.getRating())
                .categories(
                        movie.getCategories().stream()
                                .map(categoryMapper::toCategoryResponse)
                                .toList()
                )
                .streamings(
                        movie.getStreamings().stream()
                                .map(streamingMapper::toStreamingResponse)
                                .toList()
                )
                .build();
    }

}