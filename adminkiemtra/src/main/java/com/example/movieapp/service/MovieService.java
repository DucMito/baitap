package com.example.movieapp.service;

import com.example.movieapp.entity.*;
import com.example.movieapp.exception.BadRequestException;
import com.example.movieapp.model.enums.MovieType;
import com.example.movieapp.model.enums.UserRole;
import com.example.movieapp.model.request.CreateMovieRequest;
import com.example.movieapp.model.request.UpdateMovieRequest;
import com.example.movieapp.repository.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    private final CountryRepository countryRepository;

    private final GenreRepository genreRepository;

    private final ActorRepository actorRepository;

    private final DirectorRepository directorRepository;

    private final HttpSession session;

    public List<Movie> findHotMovie(Boolean status, Integer limit) {
        return movieRepository.findHotMovie(status, limit);
    }

    public Page<Movie> findByType(MovieType type, Boolean status, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("publishedAt").descending());
        Page<Movie> moviePage = movieRepository.findByTypeAndStatus(type, status, pageable);
        return moviePage;
    }

    public Movie findMovieDetails(Integer id, String slug) {
        return movieRepository.findByIdAndSlugAndStatus(id, slug, true);
    }

    public Page<Movie> findByStatuss(boolean b, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by("publishDate").descending());
        Page<Movie> moviePage = movieRepository.findByStatus(true,pageable);
        return moviePage;
    }


    public Movie createMovie(CreateMovieRequest request) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new BadRequestException("Bạn chưa đăng nhập");
        }

        if (user.getRole() != UserRole.ADMIN) {
            throw new BadRequestException("Bạn không có quyền quản trị");
        }
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Quốc gia không tồn tại"));

        // Validate and fetch genres
        List<Genre> genres = new ArrayList<>();
        if (request.getGenreIds() != null && !request.getGenreIds().isEmpty()) {
            genres = genreRepository.findAllById(request.getGenreIds());
            if (genres.size() != request.getGenreIds().size()) {
                throw new IllegalArgumentException("Một hoặc nhiều thể loại không tồn tại.");
            }
        }

        // Validate and fetch actors
        List<Actor> actors = new ArrayList<>();
        if (request.getActorIds() != null && !request.getActorIds().isEmpty()) {
            actors = actorRepository.findAllById(request.getActorIds());
            if (actors.size() != request.getActorIds().size()) {
                throw new IllegalArgumentException("Một hoặc nhiều diễn viên không tồn tại.");
            }
        }

        // Validate and fetch directors
        List<Director> directors = new ArrayList<>();
        if (request.getDirectorIds() != null && !request.getDirectorIds().isEmpty()) {
            directors = directorRepository.findAllById(request.getDirectorIds());
            if (directors.size() != request.getDirectorIds().size()) {
                throw new IllegalArgumentException("Một hoặc nhiều đạo diễn không tồn tại.");
            }
        }

        // Build Movie
        Movie movie = Movie.builder()
                .name(request.getName())
                .trailer(request.getTrailerUrl())
                .description(request.getDescription())
                .releaseYear(request.getReleaseYear())
                .type(request.getType())
                .status(request.getStatus())
                .country(country)
                .genres(genres)
                .actors(actors)
                .directors(directors)
                .build();

        // Save to DB
        return movieRepository.save(movie);

    }

    public Movie updateRequest(UpdateMovieRequest request, Integer id) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new BadRequestException("Chưa login");
        }
        if (user.getRole() != UserRole.ADMIN.ADMIN) {
            throw new BadRequestException("Chưa quyền");
        }

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy phim  id = " + id));

        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new IllegalArgumentException("Quốc gia không tồn tại"));

        List<Genre> genres = request.getGenreIds() == null ? new ArrayList<>() :
                genreRepository.findAllById(request.getGenreIds());
        if (genres.size() != request.getGenreIds().size()) {
            throw new IllegalArgumentException("không tồn tại.");
        }

        List<Actor> actors = request.getActorIds() == null ? new ArrayList<>() :
                actorRepository.findAllById(request.getActorIds());
        if (actors.size() != request.getActorIds().size()) {
            throw new IllegalArgumentException("không tồn tại.");
        }

        List<Director> directors = request.getDirectorIds() == null ? new ArrayList<>() :
                directorRepository.findAllById(request.getDirectorIds());
        if (directors.size() != request.getDirectorIds().size()) {
            throw new IllegalArgumentException("không tồn tại.");
        }

        // Cập nhật movie
        movie.setName(request.getName());
        movie.setDescription(request.getDescription());
        movie.setTrailer(request.getTrailerUrl());
        movie.setReleaseYear(request.getReleaseYear());
        movie.setType(request.getType());
        movie.setStatus(request.getStatus());
        movie.setCountry(country);
        movie.setGenres(genres);
        movie.setActors(actors);
        movie.setDirectors(directors);

        return movieRepository.save(movie);
    }

    public void daleteMovie(Integer id) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            throw new BadRequestException("Chưa login");
        }
        if (user.getRole() != UserRole.ADMIN.ADMIN) {
            throw new BadRequestException("Chưa quyền");
        }

        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Không tìm thấy phim  id = " + id));
        movieRepository.delete(movie);
    }
}
