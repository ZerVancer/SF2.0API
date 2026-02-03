package com.grupp5.sf2api.exceptions.movieSchedule;

public class TheaterIdIsEmptyException extends RuntimeException {
    public TheaterIdIsEmptyException() {
        super("TheaterId cannot be empty!");
    }
}
