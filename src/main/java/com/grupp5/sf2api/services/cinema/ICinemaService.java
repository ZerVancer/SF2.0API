package com.grupp5.sf2api.services.cinema;

import com.grupp5.sf2api.models.cinema.Cinema;
import com.grupp5.sf2api.request.cinema.UpdateCinemaRequest;
import com.grupp5.sf2api.request.movie.UpdateMovieRequest;

import java.util.List;
import java.util.UUID;

public interface ICinemaService {
  Cinema registerMovie(Cinema cinema);
  Cinema deleteMovie(UUID cinemaId);
  Cinema updateMovie(UUID cinemaId, UpdateCinemaRequest request);
  List<Cinema> getAllMovies();
}
