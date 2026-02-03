package com.grupp5.sf2api.exceptions.movieSchedule;

public class StartTimeIsEmptyException extends RuntimeException {
    public StartTimeIsEmptyException() {
        super("Start time cannot be empty!");
    }
}
