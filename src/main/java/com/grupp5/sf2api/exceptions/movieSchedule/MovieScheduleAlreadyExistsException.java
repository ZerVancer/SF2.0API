package com.grupp5.sf2api.exceptions.movieSchedule;

public class MovieScheduleAlreadyExistsException extends RuntimeException {
    public MovieScheduleAlreadyExistsException(String message) {
        super(message);
    }
}
