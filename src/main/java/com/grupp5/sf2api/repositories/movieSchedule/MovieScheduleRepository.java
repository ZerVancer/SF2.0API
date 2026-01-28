package com.grupp5.sf2api.repositories.movieSchedule;

import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, UUID> {
    Optional<MovieSchedule> findByMovieScheduleId(UUID uuid);
}
