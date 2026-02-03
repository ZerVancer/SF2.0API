package com.grupp5.sf2api.exceptions.movieSchedule;

public class MovieScheduleDoesntExistException extends RuntimeException {
    public MovieScheduleDoesntExistException() {
        super("Movie schedule doesn't exist!");
    }
}
