package com.grupp5.sf2api.exceptions.user;

public class EmailIsEmptyException extends RuntimeException {
    public EmailIsEmptyException(String message) {
        super(message);
    }
}
