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
@Table(name = "movie_schedules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID movieScheduleId;

    private LocalDateTime startTime;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    //Constructors
    public MovieSchedule(LocalDateTime startTime, Movie movie, Theater theater) {
        this.startTime = startTime;
        this.movie = movie;
        this.theater = theater;
    }
}
