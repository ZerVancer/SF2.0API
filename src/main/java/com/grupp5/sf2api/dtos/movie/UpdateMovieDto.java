package com.grupp5.sf2api.dtos.movie;

import com.grupp5.sf2api.models.movie.Movie;

public record UpdateMovieDto(
        String title,
        String description
) {
    public static UpdateMovieDto from(Movie movie) {
        return new UpdateMovieDto(
                movie.getTitle(),
                movie.getDescription()
        );
    }
}
