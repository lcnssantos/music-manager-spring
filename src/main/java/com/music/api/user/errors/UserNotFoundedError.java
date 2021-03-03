package com.music.api.user.errors;

public class UserNotFoundedError extends Exception{
    public UserNotFoundedError(String message) {
        super(message);
    }
}
