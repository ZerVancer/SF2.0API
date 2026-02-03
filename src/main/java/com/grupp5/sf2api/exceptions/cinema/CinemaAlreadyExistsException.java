package com.grupp5.sf2api.exceptions.cinema;

public class CinemaAlreadyExistsException extends RuntimeException {
    public CinemaAlreadyExistsException() {
        super("Cinema already exist.");
    }
}
