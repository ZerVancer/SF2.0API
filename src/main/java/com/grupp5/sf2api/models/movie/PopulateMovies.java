package com.grupp5.sf2api.models.movie;

import com.grupp5.sf2api.repositories.movie.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
@Order(1)
public class PopulateMovies implements CommandLineRunner {
  private MovieRepository movieRepository;

  @Override
  public void run(String... args) {
    if (!movieRepository.findAll().isEmpty()) return;

    List<Movie> movies = new ArrayList<>();

    Movie m1 = new Movie("Rental Family", 6600);
    Movie m2 = new Movie("V for Vendetta", 7980);

    movies.add(m1);
    movies.add(m2);

    movieRepository.saveAll(movies);
  }
}
