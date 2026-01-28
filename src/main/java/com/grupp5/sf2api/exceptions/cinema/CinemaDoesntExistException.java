package com.grupp5.sf2api.exceptions.cinema;

public class CinemaDoesntExistException extends RuntimeException {
  public CinemaDoesntExistException() {
    super("Cinema doesn't exists");
  }
}
