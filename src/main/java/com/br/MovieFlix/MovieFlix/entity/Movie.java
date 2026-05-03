package com.br.MovieFlix.MovieFlix.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "/movieflix/movie")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String title;
     private String description;
     private LocalDate releaseDate;
     private double rating;

     @CreationTimestamp
     @Column(name = "created_at")
     private LocalDateTime created_at;

      @UpdateTimestamp
      @Column(name = "updated_at")
      private LocalDateTime updated_at;

      @ManyToMany
      @JoinTable(
              name = "movie_category",
              joinColumns = @JoinColumn(name = "movie_id"),
              inverseJoinColumns = @JoinColumn(name = "category_id")
      )
      private Set<Category> categories;

      @ManyToMany
      @JoinTable(
              name = "movie_streaming",
              joinColumns = @JoinColumn(name = "movie_id"),
              inverseJoinColumns = @JoinColumn(name = "streaming_id")
      )
      private Set<Streaming> streamings;

}
