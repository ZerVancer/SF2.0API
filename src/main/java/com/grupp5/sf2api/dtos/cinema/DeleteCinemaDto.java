package com.grupp5.sf2api.dtos.cinema;

import com.grupp5.sf2api.models.cinema.Cinema;

import java.util.UUID;

public record DeleteCinemaDto(
        UUID cinemaId
) {
    public static DeleteCinemaDto from(Cinema cinema) {
        return new DeleteCinemaDto(cinema.getCinemaId());
    }
}
