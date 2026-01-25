package com.grupp5.sf2api.repositories.movieSchedule;

import com.grupp5.sf2api.models.movieSchedule.MovieSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, UUID> {
    
}
