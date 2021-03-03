package com.music.api.user.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class AuthUserRequest {
    @NotBlank
    @NotEmpty
    public String email;
    @NotBlank
    @NotEmpty
    public String password;
}
