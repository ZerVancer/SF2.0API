package com.grupp5.sf2api.dtos.ticket;

import com.grupp5.sf2api.models.tickets.Ticket;

import java.util.UUID;

public record UpdateTicketDto(
        UUID ticketId,
        String movieName,
        int seatValue
) {
    public static UpdateTicketDto from(Ticket ticket) {
        return new UpdateTicketDto(
          ticket.getTicketId(),
          ticket.getMovieName(),
          ticket.getSeatValue()
        );
    }
}
