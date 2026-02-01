package com.grupp5.sf2api.dtos.ticket;

import com.grupp5.sf2api.dtos.user.GetUserDto;
import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import com.grupp5.sf2api.models.tickets.Ticket;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTicketDto(
        UUID ticketId,
        Double price,
        LocalDateTime bookedAt,
        GetUserDto user,
        MovieSchedule movieSchedule,
        int seatValue
) {
    public static CreateTicketDto from(Ticket ticket) {
        return new CreateTicketDto(
                ticket.getTicketId(),
                ticket.getPrice(),
                ticket.getBookedAt(),
                GetUserDto.from(ticket.getUser()),
                ticket.getMovieSchedule(),
                ticket.getSeatValue()
        );
    }

}
