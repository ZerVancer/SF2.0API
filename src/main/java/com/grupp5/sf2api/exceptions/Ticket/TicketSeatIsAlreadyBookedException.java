package com.grupp5.sf2api.exceptions.Ticket;

public class TicketSeatIsAlreadyBookedException extends RuntimeException {
    public TicketSeatIsAlreadyBookedException() {
        super("Seat has already been booked for this movie!");
    }
}
