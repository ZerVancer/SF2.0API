package com.grupp5.sf2api.services.movie;

import com.grupp5.sf2api.models.movie.Movie;

import java.util.List;

public interface IMovieService {
  Movie registerMovie(Movie movie);
  void deleteMovie(Movie movie);
  List<Movie> getAllMovies();
}
