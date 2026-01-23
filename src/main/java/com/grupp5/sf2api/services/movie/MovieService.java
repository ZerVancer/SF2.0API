package com.grupp5.sf2api.services.movie;

import com.grupp5.sf2api.exceptions.Movie.DurationIsZeroException;
import com.grupp5.sf2api.exceptions.Movie.DurationIsNegativeException;
import com.grupp5.sf2api.exceptions.Movie.MovieAlreadyExistsException;
import com.grupp5.sf2api.exceptions.Movie.TitleIsEmptyException;
import com.grupp5.sf2api.models.movie.Movie;
import com.grupp5.sf2api.repositories.movie.MovieRepository;

import java.util.List;
import java.util.Optional;

public class MovieService implements IMovieService{

  private MovieRepository movieRepository;

  @Override
  public Movie registerMovie(Movie movie) {
    Optional<Movie> existingMovie = movieRepository.findById(movie.getMovieId());

    if (existingMovie.isPresent()) throw new MovieAlreadyExistsException("Movie already exists in database");

    if (movie.getTitle().isBlank()) throw new TitleIsEmptyException("Title cannot be empty");

    if (movie.getDurationSeconds()==0) throw new DurationIsZeroException("Duration cannot be zero");

    if (movie.getDurationSeconds()<0) throw new DurationIsNegativeException("Duration cannot be negative");

    return movieRepository.save(movie);
  }

  @Override
  public void deleteMovie(Movie movie) {
    movieRepository.delete(movie);
  }

  @Override
  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }
}
