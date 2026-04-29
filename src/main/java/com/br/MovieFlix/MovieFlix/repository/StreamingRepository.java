package com.br.MovieFlix.MovieFlix.repository;

import com.br.MovieFlix.MovieFlix.entity.Streaming;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StreamingRepository extends JpaRepository<Streaming, Long> {
}
