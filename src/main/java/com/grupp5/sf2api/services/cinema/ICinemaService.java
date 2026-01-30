package com.grupp5.sf2api.services.cinema;

import com.grupp5.sf2api.models.cinema.Cinema;
import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import com.grupp5.sf2api.request.cinema.UpdateCinemaRequest;

import java.util.List;
import java.util.UUID;

public interface ICinemaService {
  Cinema registerCinema(Cinema cinema);
  Cinema deleteCinema(UUID cinemaId);
  Cinema updateCinema(UUID cinemaId, UpdateCinemaRequest request);
  List<Cinema> getAllCinemas();
  List<MovieSchedule> getSchedule(UUID cinemaId);
}
