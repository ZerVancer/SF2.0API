package com.grupp5.sf2api.exceptions.Movie;

public class DurationIsNegativeException extends RuntimeException {
  public DurationIsNegativeException(String message) {
    super(message);
  }
}
