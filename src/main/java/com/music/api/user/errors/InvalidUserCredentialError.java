package com.music.api.user.errors;

public class InvalidUserCredentialError extends Exception {
    public InvalidUserCredentialError(String message) {
        super(message);
    }
}
