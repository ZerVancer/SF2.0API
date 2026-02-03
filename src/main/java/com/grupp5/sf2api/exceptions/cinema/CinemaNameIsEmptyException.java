package com.grupp5.sf2api.exceptions.cinema;

public class CinemaNameIsEmptyException extends RuntimeException {
    public CinemaNameIsEmptyException() {
        super("Name is empty!");
    }
}
