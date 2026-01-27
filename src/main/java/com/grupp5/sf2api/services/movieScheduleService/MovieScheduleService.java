package com.grupp5.sf2api.services.movieScheduleService;

import com.grupp5.sf2api.exceptions.movieSchedule.*;
import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import com.grupp5.sf2api.repositories.movie.MovieRepository;
import com.grupp5.sf2api.repositories.movieSchedule.MovieScheduleRepository;
import com.grupp5.sf2api.repositories.theater.TheaterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MovieScheduleService {
    private MovieScheduleRepository movieScheduleRepository;
    private TheaterRepository theaterRepository;
    private MovieRepository movieRepository;

    public MovieSchedule registerNewMovieSchedule(LocalDateTime startTime, UUID movieId, UUID theaterId) {
        MovieSchedule movieSchedule = new MovieSchedule(
                startTime,
                movieRepository.findByMovieId(movieId),
                theaterRepository.findTheaterById(theaterId)
        );

        Optional<MovieSchedule> existingMovieSchedule = movieScheduleRepository.findById(movieSchedule.getMovieScheduleId());

        if (existingMovieSchedule.isPresent()) {
            throw new MovieScheduleAlreadyExistsException("Movie schedule already exists in database!");
        }

        checkIfFieldsAreNull(movieSchedule);

        return movieScheduleRepository.save(movieSchedule);
    }

    public List<MovieSchedule> getAllMovieSchedules() {
        return movieScheduleRepository.findAll();
    }

    private void checkIfFieldsAreNull(MovieSchedule movieSchedule) {
        if (movieSchedule.getStartTime() == null) {
            throw new StartTimeIsEmptyException("Start time cannot be empty!");
        }

        if (movieSchedule.getMovie() == null) {
            throw new MovieIdIsEmptyException("MovieId cannot be empty!");
        }

        if (movieSchedule.getTheater() == null) {
            throw new TheaterIdIsEmptyException("TheaterId cannot be empty!");
        }
    }
}
