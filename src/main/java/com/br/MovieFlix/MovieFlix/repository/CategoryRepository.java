package com.br.MovieFlix.MovieFlix.repository;

import com.br.MovieFlix.MovieFlix.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
