package com.br.MovieFlix.MovieFlix.repository;

import com.br.MovieFlix.MovieFlix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
