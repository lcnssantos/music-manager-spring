package com.music.api.user.errors;

public class UserNotActiveError extends Exception {
    public UserNotActiveError(String message) {
        super(message);
    }
}
