package com.grupp5.sf2api.models.theater;

import com.grupp5.sf2api.models.cinema.Cinema;
import com.grupp5.sf2api.models.tickets.Ticket;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "theaters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID theaterId;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private int totalSeats;

    @Column (nullable = false)
    private int maxRows;

    @Column (nullable = false)
    private int maxColumns;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @OneToMany
    @JoinColumn(name = "tickets")
    private List<Ticket> tickets = new ArrayList<>();

    //Constructors
    public Theater(String name, int totalSeats, int maxRows, int maxColumns) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.maxRows = maxRows;
        this.maxColumns = maxColumns;
    }
}
