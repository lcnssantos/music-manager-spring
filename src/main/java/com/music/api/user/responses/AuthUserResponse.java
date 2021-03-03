package com.music.api.user.responses;

public class AuthUserResponse {
    public String jwt;

    public AuthUserResponse(String jwt) {
        this.jwt = jwt;
    }
}
