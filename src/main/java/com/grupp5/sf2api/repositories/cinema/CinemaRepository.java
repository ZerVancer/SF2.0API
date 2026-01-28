package com.grupp5.sf2api.repositories.cinema;

import com.grupp5.sf2api.models.cinema.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, UUID> {
  Optional<Cinema> findByCinemaId(UUID cinemaId);
}
