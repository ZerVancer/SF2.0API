package com.grupp5.sf2api.dtos.ticket;

import com.grupp5.sf2api.models.tickets.Ticket;

import java.util.UUID;

public record UpdateTicketDto(
        UUID ticketId,
        int seatValue
) {
    public static UpdateTicketDto from(Ticket ticket) {
        return new UpdateTicketDto(
          ticket.getTicketId(),
          ticket.getSeatValue()
        );
    }
}
