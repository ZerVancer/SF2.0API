package com.grupp5.sf2api.models.tickets;

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

    @Column(nullable = false)
    private UUID userId;

    // @ManyToOne(optional = false)
    // private MovieSchedule movieSchedule;

    @Column(nullable = false)
    private UUID theaterId;

    @Column(nullable = false)
    private int seatValue;

    protected Ticket() {}

    public Ticket(Double price, UUID userId, UUID theaterId, int seatValue) {
        this.bookedAt = LocalDateTime.now();
        this.price = price;
        this.userId = userId;
        this.theaterId = theaterId;
        this.seatValue = seatValue;

    }
}
