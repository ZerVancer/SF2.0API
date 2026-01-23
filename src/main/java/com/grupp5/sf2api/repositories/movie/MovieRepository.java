package com.grupp5.sf2api.repositories.movie;

import com.grupp5.sf2api.models.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

}
