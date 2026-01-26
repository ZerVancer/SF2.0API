package com.grupp5.sf2api.request.movie;

public record RegisterMovieRequest(
    String title,
    int durationSeconds,
    String description
    ) {
}
