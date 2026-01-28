package com.grupp5.sf2api.models.tickets;

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
                @UniqueConstraint(columnNames = {"movie_name", "seat_value"})
        }
)
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
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    private MovieSchedule movieSchedule;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;
    */

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

    // Ticket som ska användas i slutet
    public Ticket(String movieName, Double price, User user, Theater theater, int seatValue) {
        this.movieName = movieName;
        this.bookedAt = LocalDateTime.now();
        this.price = price;
        /*
        this.user = user;
        this.theater = theater;
        */
        this.seatValue = seatValue;
    }
}
