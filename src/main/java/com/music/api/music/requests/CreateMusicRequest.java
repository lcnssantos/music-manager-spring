package com.music.api.music.requests;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


public class CreateMusicRequest {
    @NotBlank
    @NotEmpty
    public String name;

    @Min(value = 0L, message = "The value must be positive")
    public Long genreId;

    @Min(value = 0L, message = "The value must be positive")
    public Long singerId;
}
