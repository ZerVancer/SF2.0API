package com.grupp5.sf2api.models.cinema;

import com.grupp5.sf2api.repositories.cinema.CinemaRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class PopulateCinema implements CommandLineRunner {
  private CinemaRepository cinemaRepository;

  @Override
  public void run(String... args) throws Exception {
    if (!cinemaRepository.findAll().isEmpty()) return;

    List<Cinema> cinemas = new ArrayList<>();

    Cinema c1 = new Cinema("Biopalatset", "Kungstorget");
    Cinema c2 = new Cinema("Filmstaden Bergakungen", "Skånegatan");

    cinemas.add(c1);
    cinemas.add(c2);

    cinemaRepository.saveAll(cinemas);
  }
}
