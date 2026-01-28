package com.grupp5.sf2api.request.ticket;

public record CreateTicketRequest(
        String movieName,
        Double price,
        int seatValue
) {
}
