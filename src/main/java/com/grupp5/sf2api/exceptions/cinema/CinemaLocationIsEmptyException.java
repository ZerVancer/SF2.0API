package com.grupp5.sf2api.exceptions.cinema;

public class CinemaLocationIsEmptyException extends RuntimeException {
  public CinemaLocationIsEmptyException() {
    super("Location is empty");
  }
}
