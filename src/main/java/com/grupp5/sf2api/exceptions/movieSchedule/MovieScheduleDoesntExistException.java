package com.grupp5.sf2api.exceptions.movieSchedule;

public class MovieScheduleDoesntExistException extends RuntimeException {
    public MovieScheduleDoesntExistException(String message) {
        super(message);
    }
}
