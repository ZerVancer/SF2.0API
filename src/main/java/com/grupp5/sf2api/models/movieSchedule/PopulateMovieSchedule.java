package com.grupp5.sf2api.models.movieSchedule;

import com.grupp5.sf2api.exceptions.movie.MovieDoesntExistException;
import com.grupp5.sf2api.models.movie.Movie;
import com.grupp5.sf2api.models.theater.Theater;
import com.grupp5.sf2api.repositories.movie.MovieRepository;
import com.grupp5.sf2api.repositories.movieSchedule.MovieScheduleRepository;
import com.grupp5.sf2api.repositories.theater.TheaterRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
@Order(4)
public class PopulateMovieSchedule implements CommandLineRunner {
    private MovieScheduleRepository movieScheduleRepository;
    private MovieRepository movieRepository;
    private TheaterRepository theaterRepository;

    @Override
    public void run(String... args) {
        if (!movieScheduleRepository.findAll().isEmpty()) return;

        List<MovieSchedule> schedules = new ArrayList<>();

        Movie m1 = movieRepository.findByTitle("Rental Family")
                .orElseThrow(MovieDoesntExistException::new);

        Movie m2 = movieRepository.findByTitle("V for Vendetta")
                .orElseThrow((MovieDoesntExistException::new));

        Theater t1 = theaterRepository.findByName("Salong 1")
                .orElseThrow(RuntimeException::new);

        Theater t2 = theaterRepository.findByName("Salong 2")
                .orElseThrow(RuntimeException::new);

        Theater t3 = theaterRepository.findByName("Salong 3")
                .orElseThrow(RuntimeException::new);

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
