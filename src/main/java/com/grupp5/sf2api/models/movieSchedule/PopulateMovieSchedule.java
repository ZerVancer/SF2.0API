package com.grupp5.sf2api.models.movieSchedule;

import com.grupp5.sf2api.exceptions.movie.MovieDoesntExistException;
import com.grupp5.sf2api.models.movie.Movie;
import com.grupp5.sf2api.models.theater.Theater;
import com.grupp5.sf2api.repositories.movie.MovieRepository;
import com.grupp5.sf2api.repositories.movieSchedule.MovieScheduleRepository;
import com.grupp5.sf2api.repositories.theater.TheaterRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class PopulateMovieSchedule implements CommandLineRunner {
    private MovieScheduleRepository movieScheduleRepository;
    private MovieRepository movieRepository;
    private TheaterRepository theaterRepository;

    @Override
    public void run(String... args) {
        if (!movieScheduleRepository.findAll().isEmpty()) return;

        List<MovieSchedule> schedules = new ArrayList<>();

        Movie m1 = movieRepository.findByMovieId(UUID.fromString("cbcc90b9-9587-473e-8c8a-004e318f16ff"))
                .orElseThrow(MovieDoesntExistException::new);

        Movie m2 = movieRepository.findByMovieId(UUID.fromString("304b1ae6-d24f-4cbd-864d-dc4c5acbc676"))
                .orElseThrow((MovieDoesntExistException::new));

        Theater t1 = theaterRepository.findByTheaterId(UUID.fromString("7a58627e-5c65-4fdd-939d-c804643d7448")).
                orElseThrow(RuntimeException::new);

        Theater t2 = theaterRepository.findByTheaterId(UUID.fromString("a90dab09-535d-4eb7-b9b5-7b668543a4b2")).
                orElseThrow(RuntimeException::new);

        Theater t3 = theaterRepository.findByTheaterId(UUID.fromString("5bb37295-31aa-4d95-a3cb-6277579e7551")).
                orElseThrow(RuntimeException::new);

        MovieSchedule s1 = new MovieSchedule(
                LocalDateTime.of(2026,2,1,18,30,0),
                m1,
                t1
        );

        MovieSchedule s2 = new MovieSchedule(
                LocalDateTime.of(2026, 2, 1, 18, 0, 0),
                m2,
                t2
        );

        MovieSchedule s3 = new MovieSchedule(
                LocalDateTime.of(2026, 2, 1, 21, 30, 0),
                m2,
                t3
        );

        schedules.add(s1);
        schedules.add(s2);
        schedules.add(s3);

        movieScheduleRepository.saveAll(schedules);
    }
}
