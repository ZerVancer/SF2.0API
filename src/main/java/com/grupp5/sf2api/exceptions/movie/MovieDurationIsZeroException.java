package com.grupp5.sf2api.exceptions.movie;

public class MovieDurationIsZeroException extends RuntimeException {
    public MovieDurationIsZeroException() {
        super("Duration cannot be zero!");
    }
}
