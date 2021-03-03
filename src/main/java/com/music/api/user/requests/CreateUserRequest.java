package com.music.api.user.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class CreateUserRequest {
    @NotBlank
    @NotEmpty
    public String name;

    @NotBlank
    @NotEmpty
    public String password;

    @NotBlank
    @NotEmpty
    public String passwordAgain;

    @NotBlank
    @NotEmpty
    @Email
    public String email;
}
