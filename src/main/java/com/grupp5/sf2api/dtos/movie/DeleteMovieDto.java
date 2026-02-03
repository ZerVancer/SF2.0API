package com.grupp5.sf2api.dtos.movie;

import com.grupp5.sf2api.models.movie.Movie;

import java.util.UUID;

public record DeleteMovieDto(
        UUID movieId
) {
    public static DeleteMovieDto from(Movie movie) {
        return new DeleteMovieDto(movie.getMovieId());
    }
}
