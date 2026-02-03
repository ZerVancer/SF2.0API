package com.grupp5.sf2api.services.cinema;

import com.grupp5.sf2api.exceptions.cinema.CinemaAlreadyExistsException;
import com.grupp5.sf2api.exceptions.cinema.CinemaDoesntExistException;
import com.grupp5.sf2api.exceptions.cinema.CinemaLocationIsEmptyException;
import com.grupp5.sf2api.exceptions.cinema.CinemaNameIsEmptyException;
import com.grupp5.sf2api.models.cinema.Cinema;
import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import com.grupp5.sf2api.repositories.cinema.CinemaRepository;
import com.grupp5.sf2api.repositories.movieSchedule.MovieScheduleRepository;
import com.grupp5.sf2api.request.cinema.UpdateCinemaRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CinemaService implements ICinemaService {
    private CinemaRepository cinemaRepository;
    private MovieScheduleRepository movieScheduleRepository;

    @Override
    public Cinema registerCinema(Cinema cinema) {
        Optional<Cinema> existingCinema = cinemaRepository.findByCinemaId(cinema.getCinemaId());

        if (existingCinema.isPresent()) throw new CinemaAlreadyExistsException();

        if (cinema.getName().isBlank()) throw new CinemaNameIsEmptyException();

        if (cinema.getLocation().isBlank()) throw new CinemaLocationIsEmptyException();

        return cinemaRepository.save(cinema);
    }

    @Override
    public Cinema deleteCinema(UUID cinemaId) {
        Cinema cinema = cinemaRepository
                .findByCinemaId(cinemaId)
                .orElseThrow(CinemaDoesntExistException::new);

        cinemaRepository.deleteById(cinemaId);

        return cinema;
    }

    @Override
    public Cinema updateCinema(UUID cinemaId, UpdateCinemaRequest request) {
        Cinema cinema = cinemaRepository
                .findByCinemaId(cinemaId)
                .orElseThrow(CinemaDoesntExistException::new);

        if (request.name() != null && !request.name().isBlank()) cinema.setName(request.name());

        if (request.location() != null && !request.location().isBlank()) cinema.setLocation(request.location());

        return cinemaRepository.save(cinema);
    }

    @Override
    public List<Cinema> getAllCinemas() {
        return cinemaRepository.findAll();
    }

    @Override
    public List<MovieSchedule> getSchedule(UUID cinemaId) {
        return movieScheduleRepository.findAllByTheater_Cinema_CinemaId(cinemaId);
    }
}
