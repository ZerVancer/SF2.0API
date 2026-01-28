package com.grupp5.sf2api.services.ticket;

import com.grupp5.sf2api.exceptions.Ticket.*;
import com.grupp5.sf2api.models.tickets.Ticket;
import com.grupp5.sf2api.repositories.ticket.TicketRepository;
import com.grupp5.sf2api.request.ticket.UpdateTicketRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TicketService implements ITicketService {

    private TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(Ticket ticket) {
        Optional<Ticket> existingTicket = ticketRepository.findByTicketId(ticket.getTicketId());

        if (existingTicket.isPresent()) {
            throw new TicketAlreadyExistsException();
        }

        boolean seatBooked = ticketRepository.existsByMovieNameAndSeatValueAndTicketIdNot(ticket.getMovieName(), ticket.getSeatValue(), ticket.getTicketId());

        if (seatBooked) {
            throw new TicketSeatIsAlreadyBookedException();
        }

        if (ticket.getMovieName().isBlank()) {
            throw new TicketMovieNameIsBlankException();
        }

        if (ticket.getPrice() == null || ticket.getPrice() <= 0) {
            throw new TicketPriceIsZeroOrBelowException();
        }

        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(UUID ticketId, UpdateTicketRequest request) {

        Ticket ticket = ticketRepository.findByTicketId(ticketId)
                .orElseThrow(TicketDontExistsException::new);

        String movieName =
                request.movieName() != null
                        ? request.movieName()
                        : ticket.getMovieName();

        int seatValue =
                request.seatValue() != null
                        ? request.seatValue()
                        : ticket.getSeatValue();

        if (request.seatValue() != null && request.seatValue() <= 0) {
            throw new TicketSeatValueIsZeroOrBelowException();
        }

        // change to theaterid instead of ticketid for later.
        boolean seatAlreadyBooked =
                ticketRepository.existsByMovieNameAndSeatValueAndTicketIdNot(movieName,seatValue,ticketId);

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
        Ticket ticket = ticketRepository.findByTicketId(ticketid)
                .orElseThrow(() -> new TicketDontExistsException());

        ticketRepository.delete(ticket);

        return ticket;
    }
}
