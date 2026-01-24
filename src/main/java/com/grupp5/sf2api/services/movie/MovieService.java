package com.grupp5.sf2api.services.movie;

import com.grupp5.sf2api.exceptions.Movie.*;
import com.grupp5.sf2api.models.movie.Movie;
import com.grupp5.sf2api.repositories.movie.MovieRepository;
import com.grupp5.sf2api.request.movie.UpdateMovieRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MovieService implements IMovieService{

  private MovieRepository movieRepository;

  @Override
  public Movie registerMovie(Movie movie) {
    Optional<Movie> existingMovie = movieRepository.findById(movie.getMovieId());

    if (existingMovie.isPresent()) throw new MovieAlreadyExistsException();

    if (movie.getTitle().isBlank()) throw new TitleIsEmptyException();

    if (movie.getDurationSeconds()==0) throw new DurationIsZeroException();

    if (movie.getDurationSeconds()<0) throw new DurationIsNegativeException();

    return movieRepository.save(movie);
  }

  @Override
  public Movie deleteMovie(UUID movieId) {
    Movie movie = movieRepository.findById(movieId).orElse(null);

    movieRepository.deleteById(movieId);

    return movie;
  }

  @Override
  public Movie updateMovie(UUID movieId, UpdateMovieRequest request) {
    Movie movie = movieRepository.findById(movieId).orElseThrow(MovieDoesntExistException::new);

    if (request.title() != null && !request.title().isBlank()) movie.setTitle(request.title());

    if (request.durationSeconds() > 0) movie.setDurationSeconds(request.durationSeconds());

    if (request.description() != null && !request.description().isBlank()) movie.setDescription(request.description());

    return movieRepository.save(movie);
  }

  @Override
  public List<Movie> getAllMovies() {
    return movieRepository.findAll();
  }
}
