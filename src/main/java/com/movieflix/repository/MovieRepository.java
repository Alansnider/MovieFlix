package com.movieflix.repository;

import com.movieflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select distinct m from Movie m join m.categories c where c.id = :categoryId")
    List<Movie> findByCategory(@Param("categoryId") Long categoryId);
}
