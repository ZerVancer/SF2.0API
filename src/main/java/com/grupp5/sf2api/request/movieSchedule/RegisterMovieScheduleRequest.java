package com.grupp5.sf2api.request.movieSchedule;

import com.grupp5.sf2api.models.movie.Movie;
import com.grupp5.sf2api.models.theater.Theater;

import java.time.LocalDateTime;

public record RegisterMovieScheduleRequest(
        LocalDateTime startTime,
        Movie movie,
        Theater theater
) {
}
