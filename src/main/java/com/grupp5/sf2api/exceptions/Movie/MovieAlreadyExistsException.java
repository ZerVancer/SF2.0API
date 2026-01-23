package com.grupp5.sf2api.exceptions.Movie;

public class MovieAlreadyExistsException extends RuntimeException {
  public MovieAlreadyExistsException() {super("Movie already exists in database!");}
}
