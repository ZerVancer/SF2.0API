package com.grupp5.sf2api.request.movie;

public record UpdateMovieRequest(
    String title,
    int durationSeconds,
    String description
) {
}
