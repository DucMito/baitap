package com.example.movieapp.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateEpisodeRequest {

    @NotEmpty(message = "Not null")
    private String name;

    @NotNull(message = "Not null")
    private Integer displayOrder;

    @NotNull(message = "Not null")
    private Boolean status;
}
