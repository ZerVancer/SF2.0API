package com.grupp5.sf2api.dtos.cinema;

import com.grupp5.sf2api.models.cinema.Cinema;

public record UpdateCinemaDto(
        String name,
        String Location
) {
    public static UpdateCinemaDto from(Cinema cinema) {
        return new UpdateCinemaDto(
                cinema.getName(),
                cinema.getLocation()
        );
    }
}
