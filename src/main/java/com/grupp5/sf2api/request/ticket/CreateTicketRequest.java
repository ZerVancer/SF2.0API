package com.grupp5.sf2api.request.ticket;

import java.util.UUID;

public record CreateTicketRequest(
        Double price,
        int seatValue,
        UUID movieSchedule, // <- För att skapa kopplingen till schedule
        UUID userId // <- För att skapa kopplingen till user
) {
}
