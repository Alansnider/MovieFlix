package com.br.MovieFlix.MovieFlix.service;

import com.br.MovieFlix.MovieFlix.entity.Category;
import com.br.MovieFlix.MovieFlix.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with id"));
    }

    public Category alter(Long id, Category category) {
        return categoryRepository.save(category);
    }

    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);

    }


}
