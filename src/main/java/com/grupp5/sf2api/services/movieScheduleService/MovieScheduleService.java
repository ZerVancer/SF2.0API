package com.grupp5.sf2api.services.movieScheduleService;

import com.grupp5.sf2api.exceptions.movieSchedule.MovieIdIsEmptyException;
import com.grupp5.sf2api.exceptions.movieSchedule.MovieScheduleAlreadyExistsException;
import com.grupp5.sf2api.exceptions.movieSchedule.StartTimeIsEmptyException;
import com.grupp5.sf2api.exceptions.movieSchedule.TheaterIdIsEmptyException;
import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import com.grupp5.sf2api.repositories.movieSchedule.MovieScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieScheduleService implements IMovieScheduleService {
    private MovieScheduleRepository movieScheduleRepository;

    @Override
    public MovieSchedule registerNewMovieSchedule(MovieSchedule movieSchedule) {
        Optional<MovieSchedule> existingMovieSchedule = movieScheduleRepository.findById(movieSchedule.getMovieScheduleId());

        if (existingMovieSchedule.isPresent()) {
            throw new MovieScheduleAlreadyExistsException("Movie schedule already exists in database!");
        }

        if (movieSchedule.getStartTime() == null) {
            throw new StartTimeIsEmptyException("Start time cannot be empty!");
        }

        if (movieSchedule.getMovie() == null) {
            throw new MovieIdIsEmptyException("MovieId cannot be empty!");
        }

        if (movieSchedule.getTheater() == null) {
            throw new TheaterIdIsEmptyException("TheaterId cannot be empty!");
        }

        return movieScheduleRepository.save(movieSchedule);
    }
}
