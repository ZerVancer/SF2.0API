package com.grupp5.sf2api.exceptions.Ticket;

public class TicketPriceIsZeroOrBelowException extends RuntimeException {
    public TicketPriceIsZeroOrBelowException() {
        super("Ticket price cannot be zero or below zero!");
    }
}
