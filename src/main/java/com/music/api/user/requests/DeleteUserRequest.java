package com.music.api.user.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class DeleteUserRequest {
    @NotBlank
    @NotEmpty
    public String password;

    @NotBlank
    @NotEmpty
    @Email
    public String email;
}
