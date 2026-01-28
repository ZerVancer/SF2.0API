package com.grupp5.sf2api.controllers.cinema;

import com.grupp5.sf2api.dtos.cinema.DeleteCinemaDto;
import com.grupp5.sf2api.dtos.cinema.RegisterCinemaDto;
import com.grupp5.sf2api.dtos.cinema.UpdateCinemaDto;
import com.grupp5.sf2api.models.cinema.Cinema;
import com.grupp5.sf2api.request.cinema.RegisterCinemaRequest;
import com.grupp5.sf2api.request.cinema.UpdateCinemaRequest;
import com.grupp5.sf2api.services.cinema.CinemaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cinema")
@AllArgsConstructor
public class CinemaController {
  private CinemaService cinemaService;

  @PostMapping("/register")
  public ResponseEntity<RegisterCinemaDto> registerCinema(@RequestBody RegisterCinemaRequest request) {
    Cinema cinema = new Cinema(request.name(), request.location());

    Cinema newCinema = cinemaService.registerCinema(cinema);

    return ResponseEntity.status(HttpStatus.CREATED).body(RegisterCinemaDto.from(newCinema));
  }

  @DeleteMapping("/delete/{cinemaId}")
  public ResponseEntity<DeleteCinemaDto> deleteCinema(@PathVariable UUID cinemaId) {
    Cinema cinema = cinemaService.deleteCinema(cinemaId);

    return ResponseEntity.ok(DeleteCinemaDto.from(cinema));
  }

  @PutMapping("/update/{cinemaId}")
  public ResponseEntity<UpdateCinemaDto> updateCinema(@PathVariable UUID cinemaId, @RequestBody UpdateCinemaRequest request) {
    Cinema cinema = cinemaService.updateCinema(cinemaId, request);

    return ResponseEntity.ok(UpdateCinemaDto.from(cinema));
  }

  @GetMapping("/get-all")
  public ResponseEntity<List<Cinema>> getCinemas() {
    return ResponseEntity.ok(cinemaService.getAllCinemas());
  }
}
