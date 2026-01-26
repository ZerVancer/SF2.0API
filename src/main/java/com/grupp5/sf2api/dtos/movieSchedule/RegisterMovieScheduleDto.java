package com.grupp5.sf2api.dtos.movieSchedule;

import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterMovieScheduleDto(
        UUID scheduleId,
        LocalDateTime startTime,
        String movieTitle
) {
    public static RegisterMovieScheduleDto from(MovieSchedule movieSchedule) {
        return new RegisterMovieScheduleDto(
                movieSchedule.getMovieScheduleId(),
                movieSchedule.getStartTime(),
                movieSchedule.getMovie().getTitle());
    }
}
