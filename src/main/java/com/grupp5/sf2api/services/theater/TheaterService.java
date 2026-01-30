package com.grupp5.sf2api.services.theater;

import com.grupp5.sf2api.exceptions.cinema.CinemaDoesntExistException;
import com.grupp5.sf2api.exceptions.theater.TheaterDoesntExistException;
import com.grupp5.sf2api.models.cinema.Cinema;
import com.grupp5.sf2api.models.theater.Theater;
import com.grupp5.sf2api.repositories.cinema.CinemaRepository;
import com.grupp5.sf2api.repositories.theater.TheaterRepository;
import com.grupp5.sf2api.request.theater.UpdateTheaterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TheaterService implements ITheaterService {

    private final TheaterRepository theaterRepository;
    private final CinemaRepository cinemaRepository;

    @Override
    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    @Override
    public Theater updateTheater(UUID theaterid, UpdateTheaterRequest request) {
        Theater theaterExists = theaterRepository.findByTheaterId(theaterid)
                .orElseThrow(() -> new TheaterDoesntExistException());

        if (request.name() != null && !request.name().isBlank()) {
            theaterExists.setName(request.name());
        }

        if (request.totalSeats() > 0) {
            theaterExists.setTotalSeats(request.totalSeats());
        }

        if (request.maxRows() > 0) {
            theaterExists.setMaxRows(request.maxRows());
        }

        if (request.maxColumns() > 0) {
            theaterExists.setMaxColumns(request.maxColumns());
        }

        Cinema cinema = cinemaRepository.findByCinemaId(request.cinemaId()).orElseThrow(CinemaDoesntExistException::new);
        theaterExists.setCinema(cinema);

        return theaterRepository.save(theaterExists);
        }

    @Override
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    @Override
    public Theater deleteTheater(UUID theaterid) {
        Theater deletedTheater = theaterRepository.findByTheaterId(theaterid)
                .orElseThrow(() -> new TheaterDoesntExistException());

        theaterRepository.delete(deletedTheater);

        return deletedTheater;
    }

}
