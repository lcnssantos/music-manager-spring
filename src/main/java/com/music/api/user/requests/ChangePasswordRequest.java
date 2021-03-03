package com.music.api.user.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ChangePasswordRequest {
    @NotBlank
    @NotEmpty
    @Email
    public String email;

    @NotBlank
    @NotEmpty
    public String oldPassword;

    @NotBlank
    @NotEmpty
    public String newPassword;
}
