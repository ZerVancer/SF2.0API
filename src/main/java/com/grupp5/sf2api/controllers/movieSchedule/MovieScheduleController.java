package com.grupp5.sf2api.controllers.movieSchedule;

import com.grupp5.sf2api.dtos.movieSchedule.RegisterMovieScheduleDto;
import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import com.grupp5.sf2api.request.movieSchedule.RegisterMovieScheduleRequest;
import com.grupp5.sf2api.services.movieScheduleService.MovieScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class MovieScheduleController {
    private MovieScheduleService movieScheduleService;

    @PostMapping("/register-schedule")
    public ResponseEntity<RegisterMovieScheduleDto> registerSchedule(@RequestBody RegisterMovieScheduleRequest request) {
        MovieSchedule newMovieSchedule = movieScheduleService.registerNewMovieSchedule(
                request.startTime(),
                request.movieId(),
                request.theaterId()
        );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(RegisterMovieScheduleDto.from(newMovieSchedule));
    }

    @GetMapping("/movie-schedules")
    public ResponseEntity<List<MovieSchedule>> getAllMovieSchedules() {
        return ResponseEntity.ok(movieScheduleService.getAllMovieSchedules());
    }
}
