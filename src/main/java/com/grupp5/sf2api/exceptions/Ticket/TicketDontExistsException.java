package com.grupp5.sf2api.exceptions.Ticket;

public class TicketDontExistsException extends RuntimeException {
    public TicketDontExistsException() {
        super("Ticket doesn't exist in database!");
    }
}
