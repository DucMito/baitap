package com.example.movieapp.model.request;
import com.example.movieapp.model.enums.MovieType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor

@NoArgsConstructor
@Builder
public class CreateMovieRequest {
    @NotEmpty(message = "not null")
    private String name;

    @NotEmpty(message = "not null")
    private String trailerUrl;

    @NotEmpty(message = "not null")
    private String description;

    @NotEmpty(message = "not null")
    private Integer releaseYear;

    @NotEmpty(message = "not null")
    private com.example.movieapp.model.enums.MovieType type;

    @NotEmpty(message = "not null")
    private Boolean status;

    @NotEmpty(message = "not null")
    private Integer countryId;

    // Các danh sách này có thể rỗng
    private List<Integer> genreIds;
    private List<Integer> actorIds;
    private List<Integer> directorIds;
}
