package com.grupp5.sf2api.exceptions.Movie;

public class DurationIsNegativeException extends RuntimeException {
  public DurationIsNegativeException() {
    super("Duration cannot be negative!");
  }
}
