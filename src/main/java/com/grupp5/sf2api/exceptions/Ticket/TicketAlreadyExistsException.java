package com.grupp5.sf2api.exceptions.Ticket;

public class TicketAlreadyExistsException extends RuntimeException {
    public TicketAlreadyExistsException() {
        super("Ticket already exists in database!");
    }
}
