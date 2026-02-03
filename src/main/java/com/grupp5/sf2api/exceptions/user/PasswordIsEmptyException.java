package com.grupp5.sf2api.exceptions.user;

public class PasswordIsEmptyException extends RuntimeException {
    public PasswordIsEmptyException() {
        super("Password cannot be empty!");
    }
}
