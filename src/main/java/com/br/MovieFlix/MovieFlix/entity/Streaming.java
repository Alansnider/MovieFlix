package com.br.MovieFlix.MovieFlix.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "streaming")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Streaming {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;








}
