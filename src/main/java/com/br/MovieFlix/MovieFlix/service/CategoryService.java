package com.br.MovieFlix.MovieFlix.service;

import com.br.MovieFlix.MovieFlix.controller.request.CategoryRequest;
import com.br.MovieFlix.MovieFlix.controller.response.CategoryResponse;
import com.br.MovieFlix.MovieFlix.entity.Category;
import com.br.MovieFlix.MovieFlix.mapper.CategoryMapper;
import com.br.MovieFlix.MovieFlix.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final  CategoryMapper categoryMapper;

    public List<CategoryResponse> list(){
         return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }


    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        return categoryMapper.toCategoryResponse(category);
    }

    public CategoryResponse update(CategoryRequest categoryRequest,Long id) {
        Category existing = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        existing.setName(categoryRequest.name());
        Category update = categoryRepository.save(existing);
        return categoryMapper.toCategoryResponse(update);
    }

    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category category = categoryRepository.save(categoryMapper.toCategory(categoryRequest));
        return categoryMapper.toCategoryResponse(category);
    }

    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with id: " + id));
        categoryRepository.delete(category);
    }





}