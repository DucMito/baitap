package com.example.movieapp.model.request;
import com.example.movieapp.model.enums.MovieType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UpdateMovieRequest {
    @NotEmpty(message = "not null")
    private String name;

    @NotEmpty(message = "not null")
    private String trailerUrl;

    @NotEmpty(message = "not null")
    private String description;

    @NotNull(message = "not null")
    private Integer releaseYear;

    @NotNull(message = "not null")
    private MovieType type;

    @NotNull(message = "not null")
    private Boolean status;

    @NotNull(message = "not null")
    private Integer countryId;

    // Các danh sách này có thể rỗng
    private List<Integer> genreIds;
    private List<Integer> actorIds;
    private List<Integer> directorIds;
}
