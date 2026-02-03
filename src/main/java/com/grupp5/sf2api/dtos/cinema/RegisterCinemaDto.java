package com.grupp5.sf2api.dtos.cinema;

import com.grupp5.sf2api.models.cinema.Cinema;

import java.util.UUID;

public record RegisterCinemaDto(
        UUID cinemaId,
        String name,
        String location
) {
    public static RegisterCinemaDto from(Cinema cinema) {
        return new RegisterCinemaDto(
                cinema.getCinemaId(),
                cinema.getName(),
                cinema.getLocation()
        );
    }
}
