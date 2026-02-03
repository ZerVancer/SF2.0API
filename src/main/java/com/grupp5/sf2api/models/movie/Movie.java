package com.grupp5.sf2api.models.movie;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID movieId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int durationSeconds;

    private String description;

    // Constructors
    public Movie(String title, int durationSeconds) {
        this.title = title;
        this.durationSeconds = durationSeconds;
    }

    public Movie(String title, int duration, String description) {
        this(title, duration);
        this.description = description;
    }
}
