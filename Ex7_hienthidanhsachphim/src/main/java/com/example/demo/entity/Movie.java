package com.example.demo.entity;

import com.example.demo.model.enums.MovieType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    String slug;

    @Column(columnDefinition = "TEXT")
    String description;

    String thumbnail;
    Integer releaseYear;
    Boolean status;
    String trailer;

    @Column(columnDefinition = "double default 5.0")
    Double rating;

    @Enumerated(EnumType.STRING)
    MovieType type;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    LocalDateTime publishedAt;

}
