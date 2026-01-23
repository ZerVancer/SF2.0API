package com.grupp5.sf2api.exceptions.Movie;

public class TitleIsEmptyException extends RuntimeException {
  public TitleIsEmptyException(String message) {
    super(message);
  }
}
