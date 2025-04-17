package com.example.movieapp.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateEpisodeRequest {
    @NotBlank(message = "Not null")
    private String name;

    @NotNull(message = "Not null")
    private Integer displayOrder;

    @NotNull(message = "Not null")
    private Boolean status;

    @NotNull(message = "Not null")
    private Integer movieId;
}
