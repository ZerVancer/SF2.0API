package com.grupp5.sf2api.exceptions.movieSchedule;

public class StartTimeIsEmptyException extends RuntimeException {
    public StartTimeIsEmptyException(String message) {
        super(message);
    }
}
