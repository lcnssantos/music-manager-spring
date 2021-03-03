package com.music.api.avaliation.requests;

import javax.validation.constraints.Min;

public class CreateAvaliationRequest {
    @Min(value = 0L, message = "The value must be positive")
    public Short score;

    @Min(value = 0L, message = "The value must be positive")
    public Long musicId;
}
