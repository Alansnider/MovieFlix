package com.movieflix.controller;

import com.movieflix.dto.request.CategoryRequest;
import com.movieflix.dto.response.CategoryResponse;
import com.movieflix.entity.Category;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.service.CategoryService;
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
@RequestMapping("/movieflix/category")
@RequiredArgsConstructor
@Tag(name = "Category", description = "Recurso responsável pelo gerenciamento de categorias")
@SecurityRequirement(name = "bearerAuth")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Listar categorias")
    @GetMapping
    public List<CategoryResponse> getAll() {
        return categoryService.findAll().stream().map(CategoryMapper::toResponse).toList();
    }

    @Operation(summary = "Salvar categoria")
    @PostMapping
    public ResponseEntity<CategoryResponse> save(@Valid @RequestBody CategoryRequest request) {
        Category saved = categoryService.save(CategoryMapper.toCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(CategoryMapper.toResponse(saved));
    }

    @Operation(summary = "Buscar categoria por id")
    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id) {
        return CategoryMapper.toResponse(categoryService.findById(id));
    }

    @Operation(summary = "Deletar categoria")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
