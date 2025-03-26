package com.example.demo.service;

import com.example.demo.entity.Movie;
import com.example.demo.model.enums.MovieType;
import com.example.demo.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("publishedAt").descending());
        Page<Movie> moviePage = movieRepository.findByTypeAndStatus(type, status, pageable);
        return moviePage;
    }

    public List<Movie> findByStatus(Boolean status, Integer page) {
        List<Movie> moviePage = movieRepository.findByStatus(status,page);
        return moviePage;
    }
    public Movie findByIdSlugStatus(int id, String slug) {
        Movie moviePage = movieRepository.findByIdSlugStatus(slug, id, true);
        return moviePage;
    }

    public List<Movie> findHotMovie(Boolean status, Integer limit) {
        return movieRepository.findHotMovie(status, limit);
    }

    public Movie findByIdAndSlugAndStatus(String slug, Integer id){
        return movieRepository.findByIdSlugStatus(slug, id, true);
    }

    public List<Movie> findBySlugAndStatus(MovieType type, Boolean status,Integer limit) {
        List<Movie> moviePage = movieRepository.findBySlugAndStatus(type.name(), status, limit);
        return moviePage;
    }
}
