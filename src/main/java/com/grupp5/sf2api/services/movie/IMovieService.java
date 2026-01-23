package com.grupp5.sf2api.services.movie;

import com.grupp5.sf2api.models.movie.Movie;
import com.grupp5.sf2api.request.movie.UpdateMovieRequest;

import java.util.List;
import java.util.UUID;

public interface IMovieService {
  Movie registerMovie(Movie movie);
  void deleteMovie(Movie movie);
  Movie updateMovie(UUID movieId, UpdateMovieRequest request);
  List<Movie> getAllMovies();
}
