package com.example.demo.repository;

import com.example.demo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findByName(String name);

   List<Movie> findByNameContaining(String name);

   List<Movie> findByNameContainingIgnoreCase(String name);

   List<Movie> findByRatingBetween(Double rating);

    List<Movie> findByRatingGreater(Double rating);

    List<Movie> findByRatingLessThanOrderByRatingDesc(Double rating);


}
