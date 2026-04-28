package com.br.MovieFlix.MovieFlix.controller;

import com.br.MovieFlix.MovieFlix.entity.Category;
import com.br.MovieFlix.MovieFlix.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/movieflix/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

         @GetMapping
        public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<Category> findById(@PathVariable Long id) {
            return ResponseEntity.ok(categoryService.findById(id));
        }


        @PostMapping
        public ResponseEntity<Category> save (@RequestBody Category category){
            return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.create(category));
        }

        @PutMapping("/{id}")
        public ResponseEntity<Category> alter (@PathVariable Long id, @RequestBody Category category){
            return ResponseEntity.ok().body(categoryService.alter(id, category));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> delete (@PathVariable Long id){
            categoryService.delete(id);
            return ResponseEntity.noContent().build();
        }
    }
