package com.grupp5.sf2api.exceptions.movie;

public class MovieDurationIsNegativeException extends RuntimeException {
  public MovieDurationIsNegativeException() {
    super("Duration cannot be negative!");
  }
}
