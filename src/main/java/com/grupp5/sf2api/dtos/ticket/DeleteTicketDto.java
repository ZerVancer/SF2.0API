package com.grupp5.sf2api.dtos.ticket;

import com.grupp5.sf2api.models.tickets.Ticket;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeleteTicketDto(
        UUID ticketId,
        String movieName,
        LocalDateTime bookedAt,
        double price,
        int seatValue
) {
    public static DeleteTicketDto from(Ticket ticket) {
        return new DeleteTicketDto(
                ticket.getTicketId(),
                ticket.getMovieName(),
                ticket.getBookedAt(),
                ticket.getPrice(),
                ticket.getSeatValue());
    }
}
