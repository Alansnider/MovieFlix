package com.movieflix.controller;

import com.movieflix.dto.request.MovieRequest;
import com.movieflix.dto.response.MovieResponse;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
@Tag(name = "Movie", description = "Recurso responsável pelo gerenciamento de filmes")
@SecurityRequirement(name = "bearerAuth")
public class MovieController {

    private final MovieService movieService;

    @Operation(summary = "Listar filmes")
    @GetMapping
    public List<MovieResponse> getAll() {
        return movieService.findAll().stream().map(MovieMapper::toResponse).toList();
    }

    @Operation(summary = "Salvar filme")
    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request) {
        Movie saved = movieService.save(MovieMapper.toMovie(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(MovieMapper.toResponse(saved));
    }

    @Operation(summary = "Alterar filme")
    @PutMapping("/{id}")
    public MovieResponse update(@PathVariable Long id, @Valid @RequestBody MovieRequest request) {
        return MovieMapper.toResponse(movieService.update(id, MovieMapper.toMovie(request)));
    }

    @Operation(summary = "Buscar filme por id")
    @GetMapping("/{id}")
    public MovieResponse getById(@PathVariable Long id) {
        return MovieMapper.toResponse(movieService.findById(id));
    }

    @Operation(summary = "Buscar filmes por categoria")
    @GetMapping("/search")
    public List<MovieResponse> getByCategory(@RequestParam Long category) {
        return movieService.findByCategory(category).stream().map(MovieMapper::toResponse).toList();
    }

    @Operation(summary = "Deletar filme")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
