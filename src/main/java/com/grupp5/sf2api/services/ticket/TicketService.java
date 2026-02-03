package com.grupp5.sf2api.services.ticket;

import com.grupp5.sf2api.exceptions.Ticket.*;
import com.grupp5.sf2api.exceptions.movieSchedule.MovieScheduleDoesntExistException;
import com.grupp5.sf2api.exceptions.user.UserDoesntExistException;
import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import com.grupp5.sf2api.models.tickets.Ticket;
import com.grupp5.sf2api.models.user.User;
import com.grupp5.sf2api.repositories.movieSchedule.MovieScheduleRepository;
import com.grupp5.sf2api.repositories.ticket.TicketRepository;
import com.grupp5.sf2api.repositories.user.UserRepository;
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
    private final UserRepository userRepository;
    private final MovieScheduleRepository movieScheduleRepository;

    @Override
    public Ticket createTicket(CreateTicketRequest request) {

        User user = userRepository.findById(request.userId())
                .orElseThrow(UserDoesntExistException::new);

        MovieSchedule movieSchedule = movieScheduleRepository.findByMovieScheduleId(request.movieSchedule())
                .orElseThrow(MovieScheduleDoesntExistException::new);

        UUID theaterId = movieSchedule.getTheater().getTheaterId();

        if (theaterId == null) {
            throw new MovieScheduleDoesntExistException();
        }

        boolean seatBooked = ticketRepository.existsByMovieSchedule_Theater_TheaterIdAndSeatValue(theaterId, request.seatValue());

        if (seatBooked) {
            throw new TicketSeatIsAlreadyBookedException();
        }

        if (request.price() == null ||request.price() <= 0) {
            throw new TicketPriceIsZeroOrBelowException();
        }

        Ticket ticket = new Ticket(
                request.price(),
                user,
                movieSchedule,
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

        UUID theaterId = ticket.getMovieSchedule().getTheater().getTheaterId();

        if (theaterId == null) {
            throw new MovieScheduleDoesntExistException();
        }

        boolean seatAlreadyBooked = ticketRepository.existsByMovieSchedule_Theater_TheaterIdAndSeatValueAndTicketIdNot(
                theaterId,
                newSeatValue,
                ticket.getTicketId()
        );

        if (seatAlreadyBooked) {
            throw new TicketSeatIsAlreadyBookedException();
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
                .orElseThrow(TicketDontExistsException::new);

        ticketRepository.delete(ticket);

        return ticket;
    }
}
