package com.music.api.user.errors;

public class ExpiredJWTError extends Exception{
    public ExpiredJWTError(String message) {
        super(message);
    }
}
