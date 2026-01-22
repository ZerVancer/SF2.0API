package com.grupp5.sf2api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private UUID id;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private int totalSeats;

    @Column (nullable = false)
    private int maxRows;

    @Column (nullable = false)
    private int maxColumns;

    @ManyToOne
    @JoinColumn(name = "cinemaId")
    private Cinema cinema;

    //Constructors
    public Theater(String name, int totalSeats, int maxRows, int maxColumns) {
        this.name = name;
        this.totalSeats = totalSeats;
        this.maxRows = maxRows;
        this.maxColumns = maxColumns;
    }
}
