package com.grupp5.sf2api.exceptions.Movie;

public class MovieDoesntExistException extends RuntimeException {
  public MovieDoesntExistException() {
    super("Movie not found in database!");
  }
}
