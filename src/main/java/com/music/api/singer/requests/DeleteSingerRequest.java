package com.music.api.singer.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class DeleteSingerRequest {
    @NotBlank
    @NotEmpty
    public Long id;
}
