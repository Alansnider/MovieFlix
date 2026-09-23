package com.movieflix.service;

import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.exception.ResourceNotFoundException;
import com.movieflix.repository.CategoryRepository;
import com.movieflix.repository.MovieRepository;
import com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final StreamingRepository streamingRepository;

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Transactional
    public Movie save(Movie movie) {
        movie.setCategories(resolveCategories(movie.getCategories()));
        movie.setStreamings(resolveStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    public Movie findById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado: " + id));
    }

    @Transactional
    public Movie update(Long id, Movie updated) {
        Movie movie = findById(id);
        movie.setName(updated.getName());
        movie.setDescription(updated.getDescription());
        movie.setReleaseDate(updated.getReleaseDate());
        movie.setRating(updated.getRating());
        movie.setCategories(resolveCategories(updated.getCategories()));
        movie.setStreamings(resolveStreamings(updated.getStreamings()));
        return movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    public List<Movie> findByCategory(Long categoryId) {
        return movieRepository.findByCategory(categoryId);
    }

    @Transactional
    public void deleteById(Long id) {
        Movie movie = findById(id);
        movieRepository.delete(movie);
    }

    private List<Category> resolveCategories(List<Category> categories) {
        List<Long> ids = categories.stream().map(Category::getId).toList();
        List<Category> found = categoryRepository.findAllById(ids);
        if (found.size() != new HashSet<>(ids).size()) {
            throw new ResourceNotFoundException("Uma ou mais categorias informadas não foram encontradas");
        }
        return found;
    }

    private List<Streaming> resolveStreamings(List<Streaming> streamings) {
        List<Long> ids = streamings.stream().map(Streaming::getId).toList();
        List<Streaming> found = streamingRepository.findAllById(ids);
        if (found.size() != new HashSet<>(ids).size()) {
            throw new ResourceNotFoundException("Um ou mais streamings informados não foram encontrados");
        }
        return found;
    }
}
