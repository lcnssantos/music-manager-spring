package com.music.api.singer.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CreateSingerRequest {
    @NotBlank
    @NotEmpty
    public String name;
}
