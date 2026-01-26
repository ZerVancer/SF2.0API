package com.grupp5.sf2api.repositories.ticket;

import com.grupp5.sf2api.models.tickets.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    Optional<Ticket> findByTicketId(UUID ticketId);
    boolean existsByMovieNameAndSeatValue(String movieName, int seatValue);

    boolean existsByMovieNameAndSeatValueAndTicketId(String movieName, int seatValue, UUID ticketId);
}
