package com.grupp5.sf2api.services.ticket;

import com.grupp5.sf2api.exceptions.Ticket.*;
import com.grupp5.sf2api.exceptions.theater.TheaterDoesntExistException;
import com.grupp5.sf2api.models.theater.Theater;
import com.grupp5.sf2api.models.tickets.Ticket;
import com.grupp5.sf2api.repositories.theater.TheaterRepository;
import com.grupp5.sf2api.repositories.ticket.TicketRepository;
import com.grupp5.sf2api.request.ticket.CreateTicketRequest;
import com.grupp5.sf2api.request.ticket.UpdateTicketRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TicketService implements ITicketService {

    private TicketRepository ticketRepository;
    private final TheaterRepository theaterRepository;

    @Override
    public Ticket createTicket(CreateTicketRequest request) {
        Theater theater = theaterRepository.findByTheaterId(request.theaterId())
                .orElseThrow(() -> new TheaterDoesntExistException());

        boolean seatBooked = ticketRepository.existsByTheaterAndSeatValue(theater, request.seatValue());

        if (seatBooked) {
            throw new TicketSeatIsAlreadyBookedException();
        }

        if (request.movieName() == null || request.movieName().isBlank()) {
            throw new TicketMovieNameIsBlankException();
        }

        if (request.price() == null ||request.price() <= 0) {
            throw new TicketPriceIsZeroOrBelowException();
        }

        Ticket ticket = new Ticket(
                request.movieName(),
                request.price(),
                theater,
                request.seatValue()
        );

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(UUID ticketId, UpdateTicketRequest request) {

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(TicketDontExistsException::new);

        int newSeatValue = request.seatValue() != null ? request.seatValue() : ticket.getSeatValue();

        if (request.seatValue() != null && request.seatValue() <= 0) {
            throw new TicketSeatValueIsZeroOrBelowException();
        }

        boolean seatAlreadyBooked = ticketRepository.existsByTheaterAndSeatValueAndTicketIdNot(
                ticket.getTheater(),
                newSeatValue,
                ticket.getTicketId()
        );

        if (seatAlreadyBooked) {
            throw new TicketSeatIsAlreadyBookedException();
        }

        if (request.movieName() != null && !request.movieName().isBlank()) {
            ticket.setMovieName(request.movieName());
        }

        if (request.seatValue() != null) {
            ticket.setSeatValue(request.seatValue());
        }

        return ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket deleteTicket(UUID ticketid) {
        Ticket ticket = ticketRepository.findById(ticketid)
                .orElseThrow(() -> new TicketDontExistsException());

        ticketRepository.delete(ticket);

        return ticket;
    }
}
