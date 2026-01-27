package com.grupp5.sf2api.repositories.movie;

import com.grupp5.sf2api.models.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {
  Optional<Movie> findByMovieId(UUID movieId);
}
