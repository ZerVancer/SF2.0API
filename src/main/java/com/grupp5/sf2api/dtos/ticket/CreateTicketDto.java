package com.grupp5.sf2api.dtos.ticket;

import com.grupp5.sf2api.models.theater.Theater;
import com.grupp5.sf2api.models.tickets.Ticket;
import com.grupp5.sf2api.models.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTicketDto(
        UUID ticketId,
        String movieName,
        Double price,
        LocalDateTime bookedAt,
        User user,
        Theater theater,
        int seatValue
) {
    public static CreateTicketDto from(Ticket ticket) {
        return new CreateTicketDto(
                ticket.getTicketId(),
                ticket.getMovieName(),
                ticket.getPrice(),
                ticket.getBookedAt(),
                ticket.getUser(),
                ticket.getTheater(),
                ticket.getSeatValue()
        );
    }

}
