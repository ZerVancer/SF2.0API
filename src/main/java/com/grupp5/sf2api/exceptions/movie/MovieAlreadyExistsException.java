package com.grupp5.sf2api.exceptions.movie;

public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException() {
        super("Movie already exists in database!");
    }
}
