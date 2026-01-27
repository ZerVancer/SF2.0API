package com.grupp5.sf2api.dtos.movieSchedule;

import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;

import java.time.LocalDateTime;
import java.util.UUID;

public record DeleteMovieScheduleDto(
        UUID movieScheduleId,
        LocalDateTime startTime,
        String movieName,
        String theaterName
) {
    public static DeleteMovieScheduleDto from(MovieSchedule movieSchedule) {
        return new DeleteMovieScheduleDto(
                movieSchedule.getMovieScheduleId(),
                movieSchedule.getStartTime(),
                movieSchedule.getMovie().getTitle(),
                movieSchedule.getTheater().getName());
    }
}
