package com.example.demo.entity;

import com.example.demo.model.enums.MovieType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String thumbnail;
    private Integer releaseYear;
    private Boolean status;

    @Column(columnDefinition = "double default 5.0")
    private Double rating;

    @Enumerated(EnumType.STRING)
    private MovieType type;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private LocalDateTime publishedAt;

}
