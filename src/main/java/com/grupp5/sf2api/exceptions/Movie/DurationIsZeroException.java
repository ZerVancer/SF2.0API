package com.grupp5.sf2api.exceptions.Movie;

public class DurationIsZeroException extends RuntimeException {
  public DurationIsZeroException(String message) {
    super(message);
  }
}
