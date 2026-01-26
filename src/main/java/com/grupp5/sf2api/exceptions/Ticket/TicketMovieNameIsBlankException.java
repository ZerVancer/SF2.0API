package com.grupp5.sf2api.exceptions.Ticket;

public class TicketMovieNameIsBlankException extends RuntimeException {
    public TicketMovieNameIsBlankException() {
        super("The name of the movie cannot be blank or null!");
    }
}
