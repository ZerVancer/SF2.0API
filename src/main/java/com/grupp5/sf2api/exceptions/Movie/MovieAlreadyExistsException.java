package com.grupp5.sf2api.exceptions.Movie;

public class MovieAlreadyExistsException extends RuntimeException {
  public MovieAlreadyExistsException(String message) {
    super(message);
  }
}
