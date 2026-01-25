package com.grupp5.sf2api.models.movieSchedule;

import com.grupp5.sf2api.models.movie.Movie;
import com.grupp5.sf2api.models.theater.Theater;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "movieSchedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID movieScheduleId;

    @Column
    LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "movieId")
    Movie movie;

    @ManyToOne
    @JoinColumn(name = "theaterId")
    Theater theater;

    //Constructors
    public MovieSchedule(LocalDateTime startTime, Movie movie, Theater theater) {
        this.startTime = startTime;
        this.movie = movie;
        this.theater = theater;
    }
}
