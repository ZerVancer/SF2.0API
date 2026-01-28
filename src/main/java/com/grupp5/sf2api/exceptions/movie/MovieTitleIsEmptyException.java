package com.grupp5.sf2api.exceptions.movie;

public class MovieTitleIsEmptyException extends RuntimeException {
  public MovieTitleIsEmptyException() {
    super("Title cannot be empty!");
  }
}
