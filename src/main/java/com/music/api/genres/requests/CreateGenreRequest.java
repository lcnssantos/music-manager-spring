package com.music.api.genres.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CreateGenreRequest {
    @NotBlank
    @NotEmpty
    public String name;
}
