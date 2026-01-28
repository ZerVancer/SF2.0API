package com.grupp5.sf2api.repositories.theater;

import com.grupp5.sf2api.models.theater.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, UUID> {
}
