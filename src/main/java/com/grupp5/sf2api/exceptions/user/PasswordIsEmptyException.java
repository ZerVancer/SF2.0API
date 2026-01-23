package com.grupp5.sf2api.exceptions.user;

public class PasswordIsEmptyException extends RuntimeException {
    public PasswordIsEmptyException(String message) {
        super(message);
    }
}
