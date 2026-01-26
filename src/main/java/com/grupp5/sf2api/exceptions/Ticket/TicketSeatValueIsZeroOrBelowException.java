package com.grupp5.sf2api.exceptions.Ticket;

public class TicketSeatValueIsZeroOrBelowException extends RuntimeException {
    public TicketSeatValueIsZeroOrBelowException() {
        super("The seat value cannot be zero or below zero!");
    }
}
