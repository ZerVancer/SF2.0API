package com.grupp5.sf2api.models.tickets;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.grupp5.sf2api.models.theater.Theater;
import com.grupp5.sf2api.models.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "tickets",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"theater_id", "seat_value"})
        })
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ticketId;

    // Vill ha något mer än priset för biljetten för requests etc.
    // Ta bort efter movie har en koppling till ticket.
    @Column(name = "movie_name",nullable = false)
    private String movieName;

    @Column(nullable = false)
    private LocalDateTime bookedAt;

    @Column(nullable = false)
    private Double price;

    /*
    @ManyToOne(optional = false)
    private MovieSchedule movieSchedule;
    */

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    @JsonBackReference
    private Theater theater;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @Column(name = "seat_value", nullable = false)
    private int seatValue;

    protected Ticket() {}

    // Placeholder för att testa endpointen utan allt på plats
    // både i koden och databasen.
    public Ticket(String movieName, Double price, int seatValue) {
        this.movieName = movieName;
        this.price = price;
        this.seatValue = seatValue;
        this.bookedAt = LocalDateTime.now();
    }

    // Ticket som ska användas i slutet, lägg till User user i constructorn
    public Ticket(String movieName, Double price, Theater theater, User user, int seatValue) {
        this.movieName = movieName;
        this.bookedAt = LocalDateTime.now();
        this.price = price;
        this.user = user;
        this.theater = theater;
        this.seatValue = seatValue;
    }
}
