package com.grupp5.sf2api.repositories.ticket;

import com.grupp5.sf2api.models.theater.Theater;
import com.grupp5.sf2api.models.tickets.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    boolean existsByMovieSchedule_Theater_TheaterIdAndSeatValue(UUID theaterId, int seatValue);
    boolean existsByMovieSchedule_Theater_TheaterIdAndSeatValueAndTicketIdNot(UUID theaterId, int seatValue, UUID ticketId);
    List<Ticket> findAllByMovieSchedule_Theater_TheaterId(UUID theaterId);
}
