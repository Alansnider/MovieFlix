package com.br.MovieFlix.MovieFlix.service;

import com.br.MovieFlix.MovieFlix.controller.request.CategoryRequest;
import com.br.MovieFlix.MovieFlix.controller.response.CategoryResponse;
import com.br.MovieFlix.MovieFlix.entity.Category;
import com.br.MovieFlix.MovieFlix.repository.MovieRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<CategoryResponse> list(){
        return movieRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }


    public CategoryResponse findById(Long id) {
        Category category = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        return categoryMapper.toCategoryResponse(category);
    }

    public CategoryResponse update(CategoryRequest categoryRequest, Long id) {
        Category existing = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        existing.setName(categoryRequest.name());
        Category update = movieRepository.save(existing);
        return categoryMapper.toCategoryResponse(update);
    }

    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = movieRepository.save(categoryMapper.toCategory(categoryRequest));
        return categoryMapper.toCategoryResponse(category);
    }

    public void deleteById(Long id) {
        Category category = movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        categoryRepository.delete(category);
    }




}
