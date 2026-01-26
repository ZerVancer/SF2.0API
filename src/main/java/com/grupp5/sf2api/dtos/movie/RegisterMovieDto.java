package com.grupp5.sf2api.dtos.movie;

import com.grupp5.sf2api.models.movie.Movie;

import java.util.UUID;

public record RegisterMovieDto(
    UUID movieId
) {
  public static RegisterMovieDto from(Movie movie) {
    return new RegisterMovieDto(movie.getMovieId());
  }
}
