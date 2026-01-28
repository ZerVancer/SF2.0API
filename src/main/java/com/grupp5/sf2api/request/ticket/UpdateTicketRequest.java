package com.grupp5.sf2api.request.ticket;

public record UpdateTicketRequest(
        String movieName,
        Integer seatValue
) {
}
