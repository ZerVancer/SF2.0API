package com.grupp5.sf2api.request.ticket;

import java.util.UUID;

public record CreateTicketRequest(
        String movieName,
        Double price,
        int seatValue,
        UUID theaterId // <- för att skapa kopplingen till theater
) {
}
