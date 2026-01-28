package com.grupp5.sf2api.request.movieSchedule;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterMovieScheduleRequest(
        LocalDateTime startTime,
        UUID movieId,
        UUID theaterId
) {
}
