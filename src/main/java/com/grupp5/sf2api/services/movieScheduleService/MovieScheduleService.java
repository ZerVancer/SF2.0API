package com.grupp5.sf2api.services.movieScheduleService;

import com.grupp5.sf2api.exceptions.Movie.MovieDoesntExistException;
import com.grupp5.sf2api.exceptions.movieSchedule.*;
import com.grupp5.sf2api.exceptions.theater.TheaterDoesntExistException;
import com.grupp5.sf2api.models.movie.Movie;
import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import com.grupp5.sf2api.models.theater.Theater;
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
        Movie movie = movieRepository.findByMovieId(movieId)
                .orElseThrow(() -> new MovieDoesntExistException());

        Theater theater = theaterRepository.findByTheaterId(theaterId)
                .orElseThrow(() -> new TheaterDoesntExistException("Theater doesn't exist!"));

        MovieSchedule movieSchedule = new MovieSchedule(
                startTime,
                movie,
                theater
        );

        Optional<MovieSchedule> existingMovieSchedule =
                movieScheduleRepository.findByMovieScheduleId(movieSchedule.getMovieScheduleId());

        if (existingMovieSchedule.isPresent()) {
            throw new MovieScheduleAlreadyExistsException("Movie schedule already exists in database!");
        }

        checkIfFieldsAreNull(movieSchedule);

        return movieScheduleRepository.save(movieSchedule);
    }

    public MovieSchedule updateMovieSchedule(UUID movieScheduleId, LocalDateTime startTime, UUID movieId, UUID theaterId) {
        MovieSchedule movieSchedule = movieScheduleRepository.findByMovieScheduleId(movieScheduleId)
                .orElseThrow(() -> new MovieScheduleDoesntExistException("Movie schedule doesn't exist!"));

        Movie movie = movieRepository.findByMovieId(movieId)
                .orElseThrow(() -> new MovieDoesntExistException());

        Theater theater = theaterRepository.findByTheaterId(theaterId)
                .orElseThrow(() -> new TheaterDoesntExistException("Theater doesn't exist!"));

        movieSchedule.setStartTime(startTime);
        movieSchedule.setMovie(movie);
        movieSchedule.setTheater(theater);

        checkIfFieldsAreNull(movieSchedule);

        return movieScheduleRepository.save(movieSchedule);
    }

    public MovieSchedule deleteMovieSchedule(UUID movieScheduleId) {
        MovieSchedule movieSchedule = movieScheduleRepository.findByMovieScheduleId(movieScheduleId)
                .orElseThrow(() -> new MovieScheduleDoesntExistException("Movie schedule doesn't exist!"));

        movieScheduleRepository.deleteById(movieScheduleId);

        return movieSchedule;
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
