package com.grupp5.sf2api.exceptions.movieSchedule;

public class MovieIdIsEmptyException extends RuntimeException {
    public MovieIdIsEmptyException() {
        super("movieId cannot be empty!");
    }
}
