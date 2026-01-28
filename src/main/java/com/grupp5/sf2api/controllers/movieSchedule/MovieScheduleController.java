package com.grupp5.sf2api.controllers.movieSchedule;

import com.grupp5.sf2api.dtos.movieSchedule.DeleteMovieScheduleDto;
import com.grupp5.sf2api.dtos.movieSchedule.RegisterMovieScheduleDto;
import com.grupp5.sf2api.dtos.movieSchedule.UpdateMovieScheduleDto;
import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import com.grupp5.sf2api.request.movieSchedule.RegisterMovieScheduleRequest;
import com.grupp5.sf2api.request.movieSchedule.UpdateMovieScheduleRequest;
import com.grupp5.sf2api.services.movieScheduleService.MovieScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
                .body(RegisterMovieScheduleDto
                        .from(newMovieSchedule)
                );
    }

    @PutMapping("/update-schedule/{scheduleId}")
    public ResponseEntity<UpdateMovieScheduleDto> updateSchedule(
            @PathVariable UUID scheduleId,
            @RequestBody UpdateMovieScheduleRequest request) {
        MovieSchedule updatedMovieSchedule = movieScheduleService.updateMovieSchedule(
                scheduleId,
                request.startTime(),
                request.movieId(),
                request.theaterId()
        );

        return ResponseEntity.ok(UpdateMovieScheduleDto
                .from(updatedMovieSchedule)
        );
    }

    @DeleteMapping("/delete-schedule/{scheduleId}")
    public ResponseEntity<DeleteMovieScheduleDto> deleteSchedule(@PathVariable UUID scheduleId) {
        MovieSchedule deletedMovieSchedule = movieScheduleService.deleteMovieSchedule(scheduleId);

        return ResponseEntity.ok(DeleteMovieScheduleDto.
                from(deletedMovieSchedule)
        );
    }

    @GetMapping("/movie-schedules")
    public ResponseEntity<List<MovieSchedule>> getAllMovieSchedules() {
        return ResponseEntity.ok(movieScheduleService.getAllMovieSchedules());
    }
}
