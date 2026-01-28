package com.grupp5.sf2api.exceptions.theater;

public class TheaterDoesntExistException extends RuntimeException {
    public TheaterDoesntExistException() {
        super("Theater doesn't exist in database!");
    }
}
