package com.br.MovieFlix.MovieFlix.service;

import com.br.MovieFlix.MovieFlix.controller.request.CategoryRequest;
import com.br.MovieFlix.MovieFlix.controller.response.CategoryResponse;
import com.br.MovieFlix.MovieFlix.entity.Category;
import com.br.MovieFlix.MovieFlix.mapper.MovieMapper;
import com.br.MovieFlix.MovieFlix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final CategoryService categoryService;
    private final StreamingService streamingService;












}
