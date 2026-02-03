package com.grupp5.sf2api.services.movieScheduleService;

import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IMovieScheduleService {
    MovieSchedule registerNewMovieSchedule(LocalDateTime startTime, UUID movieId, UUID theaterId);
    MovieSchedule updateMovieSchedule(UUID movieScheduleId, LocalDateTime startTime, UUID movieId, UUID theaterId);
    MovieSchedule deleteMovieSchedule(UUID movieScheduleId);
    List<MovieSchedule> getAllMovieSchedules();


}
