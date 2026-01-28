package com.grupp5.sf2api.services.cinema;

import com.grupp5.sf2api.exceptions.cinema.CinemaAlreadyExistsException;
import com.grupp5.sf2api.exceptions.cinema.CinemaDoesntExistException;
import com.grupp5.sf2api.exceptions.cinema.CinemaLocationIsEmptyException;
import com.grupp5.sf2api.exceptions.cinema.CinemaNameIsEmptyException;
import com.grupp5.sf2api.models.cinema.Cinema;
import com.grupp5.sf2api.repositories.cinema.CinemaRepository;
import com.grupp5.sf2api.request.cinema.UpdateCinemaRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CinemaService implements ICinemaService{

  private CinemaRepository cinemaRepository;

  @Override
  public Cinema registerMovie(Cinema cinema) {
    Optional<Cinema> existingCinema = cinemaRepository.findByCinemaId(cinema.getCinemaId());

    if (existingCinema.isPresent()) throw new CinemaAlreadyExistsException();

    if (cinema.getName().isBlank()) throw new CinemaNameIsEmptyException();

    if (cinema.getLocation().isBlank()) throw new CinemaLocationIsEmptyException();

    return cinemaRepository.save(cinema);
  }

  @Override
  public Cinema deleteMovie(UUID cinemaId) {
    Cinema cinema = cinemaRepository.findByCinemaId(cinemaId).orElse(null);

    cinemaRepository.deleteById(cinemaId);

    return cinema;
  }

  @Override
  public Cinema updateMovie(UUID cinemaId, UpdateCinemaRequest request) {
    Cinema cinema = cinemaRepository.findByCinemaId(cinemaId).orElseThrow(CinemaDoesntExistException::new);

    if (request.name() != null && !request.name().isBlank()) cinema.setName(request.name());

    if (request.location() != null && !request.location().isBlank()) cinema.setLocation(request.location());

    return cinemaRepository.save(cinema);
  }

  @Override
  public List<Cinema> getAllMovies() {
    return List.of();
  }
}
