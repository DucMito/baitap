package com.example.movieapp.api;

import com.example.movieapp.entity.Movie;
import com.example.movieapp.model.request.CreateMovieRequest;
import com.example.movieapp.model.request.UpdateMovieRequest;
import com.example.movieapp.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/movies")
public class AdminMovieApi {
    private final MovieService movieService;
    @GetMapping()
    public ResponseEntity<?> getAllMovies(@RequestParam(defaultValue = "1") Integer page, @RequestParam (defaultValue = "10") Integer pageSize) {

        Page<Movie> moviePage = movieService.findByStatuss(true,page,pageSize);
        return ResponseEntity.ok(moviePage);
    }
    @PostMapping("")
    public ResponseEntity<?> addMovie(@RequestBody CreateMovieRequest createMovieRequest) {
        Movie movie = movieService.createMovie(createMovieRequest);
        return ResponseEntity.ok(movie);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody UpdateMovieRequest updateMovieRequest) {
        Movie movie = movieService.updateRequest(updateMovieRequest,id);
        return ResponseEntity.ok(movie);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {

        movieService.daleteMovie(id);
        return ResponseEntity.ok().build();
    }
}
