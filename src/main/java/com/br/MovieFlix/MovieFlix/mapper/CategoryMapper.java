package com.br.MovieFlix.MovieFlix.mapper;

import com.br.MovieFlix.MovieFlix.controller.request.CategoryRequest;
import com.br.MovieFlix.MovieFlix.controller.response.CategoryResponse;
import com.br.MovieFlix.MovieFlix.entity.Category;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {


        public static Category toCategory(CategoryRequest categoryRequest) {
            return Category.builder()
                    .name(categoryRequest.name())
                    .build();
        }
        public static CategoryResponse  toCategoryResponse(Category category) {
            return  CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .build();

        }




}
