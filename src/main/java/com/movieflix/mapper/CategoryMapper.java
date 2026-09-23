package com.movieflix.mapper;

import com.movieflix.dto.request.CategoryRequest;
import com.movieflix.dto.response.CategoryResponse;
import com.movieflix.entity.Category;

public final class CategoryMapper {

    private CategoryMapper() {
    }

    public static Category toCategory(CategoryRequest request) {
        return Category.builder().name(request.name()).build();
    }

    public static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
