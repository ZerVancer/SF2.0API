package com.grupp5.sf2api.models.tickets;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import com.grupp5.sf2api.models.theater.Theater;
import com.grupp5.sf2api.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ticketId;

    @Column(nullable = false)
    private LocalDateTime bookedAt;

    @Column(nullable = false)
    private Double price;

    @ManyToOne(optional = false)
    @JsonBackReference
    private MovieSchedule movieSchedule;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "seat_value", nullable = false)
    private int seatValue;

    protected Ticket() {}

    public Ticket(Double price, User user, MovieSchedule movieSchedule, int seatValue) {
        this.bookedAt = LocalDateTime.now();
        this.price = price;
        this.user = user;
        this.movieSchedule = movieSchedule;
        this.seatValue = seatValue;
    }
}
