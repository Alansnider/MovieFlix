package com.br.MovieFlix.MovieFlix.controller;

import com.br.MovieFlix.MovieFlix.controller.request.CategoryRequest;
import com.br.MovieFlix.MovieFlix.controller.request.MovieRequest;
import com.br.MovieFlix.MovieFlix.controller.response.CategoryResponse;
import com.br.MovieFlix.MovieFlix.controller.response.MovieResponse;
import com.br.MovieFlix.MovieFlix.entity.Movie;
import com.br.MovieFlix.MovieFlix.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> list() {
        return ResponseEntity.ok(movieService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.findById(id));
    }

    @PostMapping
    public ResponseEntity<MovieResponse> save(@Valid @RequestBody MovieRequest request) {
        Movie savedMovie = movieService.save(request);
        MovieResponse response = MovieResponse.fromEntity(savedMovie);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(
            @RequestBody CategoryRequest request,
            @Valid @PathVariable Long id) {

        return ResponseEntity.ok(movieService.update(request, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.deleteById(id);
        return ResponseEntity.noContent().build();
    }







}
