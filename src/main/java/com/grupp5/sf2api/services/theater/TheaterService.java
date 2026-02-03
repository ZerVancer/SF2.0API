package com.grupp5.sf2api.services.theater;

import com.grupp5.sf2api.exceptions.cinema.CinemaDoesntExistException;
import com.grupp5.sf2api.exceptions.theater.TheaterDoesntExistException;
import com.grupp5.sf2api.models.cinema.Cinema;
import com.grupp5.sf2api.models.theater.Theater;
import com.grupp5.sf2api.repositories.cinema.CinemaRepository;
import com.grupp5.sf2api.repositories.theater.TheaterRepository;
import com.grupp5.sf2api.request.theater.UpdateTheaterRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TheaterService implements ITheaterService {
    private TheaterRepository theaterRepository;
    private CinemaRepository cinemaRepository;

    @Override
    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    @Override
    public Theater updateTheater(UUID theaterId, UpdateTheaterRequest request) {
        Theater theater = theaterRepository.findByTheaterId(theaterId)
                .orElseThrow(TheaterDoesntExistException::new);

        if (request.name() != null && !request.name().isBlank()) theater.setName(request.name());


        if (request.totalSeats() > 0) theater.setTotalSeats(request.totalSeats());


        if (request.maxRows() > 0) theater.setMaxRows(request.maxRows());


        if (request.maxColumns() > 0) theater.setMaxColumns(request.maxColumns());

        Cinema cinema = cinemaRepository
                .findByCinemaId(request.cinemaId())
                .orElseThrow(CinemaDoesntExistException::new);
        theater.setCinema(cinema);

        return theaterRepository.save(theater);
        }

    @Override
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    @Override
    public Theater deleteTheater(UUID theaterId) {
        Theater deletedTheater = theaterRepository.findByTheaterId(theaterId)
                .orElseThrow(TheaterDoesntExistException::new);

        theaterRepository.delete(deletedTheater);

        return deletedTheater;
    }
}
