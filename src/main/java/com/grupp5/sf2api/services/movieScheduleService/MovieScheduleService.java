package com.grupp5.sf2api.services.movieScheduleService;

import com.grupp5.sf2api.exceptions.movie.MovieDoesntExistException;
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
public class MovieScheduleService implements IMovieScheduleService {
    private MovieScheduleRepository movieScheduleRepository;
    private TheaterRepository theaterRepository;
    private MovieRepository movieRepository;

    public MovieSchedule registerNewMovieSchedule(LocalDateTime startTime, UUID movieId, UUID theaterId) {
        Movie movie = movieRepository
                .findByMovieId(movieId)
                .orElseThrow(MovieDoesntExistException::new);

        Theater theater = theaterRepository
                .findByTheaterId(theaterId)
                .orElseThrow(TheaterDoesntExistException::new);

        MovieSchedule movieSchedule = new MovieSchedule(
                startTime,
                movie,
                theater
        );

        Optional<MovieSchedule> existingMovieSchedule = movieScheduleRepository
                .findByMovieScheduleId(movieSchedule.getMovieScheduleId());

        if (existingMovieSchedule.isPresent()) throw new MovieScheduleAlreadyExistsException();

        if (movieSchedule.getStartTime() == null) throw new StartTimeIsEmptyException();

        if (movieSchedule.getMovie() == null) throw new MovieIdIsEmptyException();

        if (movieSchedule.getTheater() == null) throw new TheaterIdIsEmptyException();

        return movieScheduleRepository.save(movieSchedule);
    }

    public MovieSchedule deleteMovieSchedule(UUID movieScheduleId) {
        MovieSchedule movieSchedule = movieScheduleRepository
                .findByMovieScheduleId(movieScheduleId)
                .orElseThrow(MovieScheduleDoesntExistException::new);

        movieScheduleRepository.deleteById(movieScheduleId);

        return movieSchedule;
    }

    public MovieSchedule updateMovieSchedule(UUID movieScheduleId, LocalDateTime startTime, UUID movieId, UUID theaterId) {
        MovieSchedule movieSchedule = movieScheduleRepository
                .findByMovieScheduleId(movieScheduleId)
                .orElseThrow(MovieScheduleDoesntExistException::new);

        if (startTime != null) movieSchedule.setStartTime(startTime);

        if (movieId != null) {
            Movie movie = movieRepository
                    .findByMovieId(movieId)
                    .orElseThrow(MovieDoesntExistException::new);
            movieSchedule.setMovie(movie);
        }

        if (theaterId != null) {
            Theater theater = theaterRepository
                    .findByTheaterId(theaterId)
                    .orElseThrow(TheaterDoesntExistException::new);
            movieSchedule.setTheater(theater);
        }

        return movieScheduleRepository.save(movieSchedule);
    }

    public List<MovieSchedule> getAllMovieSchedules() {
        return movieScheduleRepository.findAll();
    }
}
