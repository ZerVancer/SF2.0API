package com.grupp5.sf2api.controllers.theater;

import com.grupp5.sf2api.dtos.theater.CreateTheaterDto;
import com.grupp5.sf2api.dtos.theater.DeleteTheaterDto;
import com.grupp5.sf2api.dtos.theater.UpdateTheaterDto;
import com.grupp5.sf2api.exceptions.cinema.CinemaDoesntExistException;
import com.grupp5.sf2api.models.cinema.Cinema;
import com.grupp5.sf2api.models.theater.Theater;
import com.grupp5.sf2api.repositories.cinema.CinemaRepository;
import com.grupp5.sf2api.request.theater.CreateTheaterRequest;
import com.grupp5.sf2api.request.theater.UpdateTheaterRequest;
import com.grupp5.sf2api.services.cinema.CinemaService;
import com.grupp5.sf2api.services.theater.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/theater")
@RequiredArgsConstructor
public class TheaterController {

    private final TheaterService theaterService;
    private final CinemaService cinemaService;

    @PostMapping("/create")
    public ResponseEntity<CreateTheaterDto> createTheater(@RequestBody CreateTheaterRequest request) {
        Cinema cinema = cinemaService.findCinemaById(request.cinemaId());

        Theater newTheater = new Theater(request.name(), request.totalSeats(), request.maxRows(), request.maxColumns(), cinema);

        Theater theater = theaterService.createTheater(newTheater);

        return ResponseEntity.ok(CreateTheaterDto.from(theater));
    }


    @PutMapping("/update/{theaterId}")
    public ResponseEntity<UpdateTheaterDto> updateTheater(
            @PathVariable UUID theaterId,
            @RequestBody UpdateTheaterRequest request) {
        Theater updateTheater = theaterService.updateTheater(theaterId, request);

        return ResponseEntity.ok(UpdateTheaterDto.from(updateTheater));
    }


    @GetMapping("/theaters")
    public ResponseEntity<List<Theater>> getAllTheaters() {
        List<Theater> theaters = theaterService.getAllTheaters();

        if (theaters.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(theaters);
    }

    @DeleteMapping("/delete/{theaterId}")
    public ResponseEntity<DeleteTheaterDto> deleteTheater(@PathVariable UUID theaterId) {
        Theater theater = theaterService.deleteTheater(theaterId);

        return ResponseEntity.ok(DeleteTheaterDto.from(theater));
    }
}
