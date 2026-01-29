package com.grupp5.sf2api.exceptions.user;

public class UserDoesntExistException extends RuntimeException {
    public UserDoesntExistException() {
        super("User doesn't exist in database!");
    }
}
