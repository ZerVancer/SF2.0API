package com.grupp5.sf2api.dtos.theater;

import com.grupp5.sf2api.models.theater.Theater;

import java.util.UUID;

public record UpdateTheaterDto(
        UUID theaterId,
        String name,
        int totalSeats,
        int maxRows,
        int maxColumns
) {
    public static UpdateTheaterDto from(Theater theater) {
        return new UpdateTheaterDto(
                theater.getTheaterId(),
                theater.getName(),
                theater.getTotalSeats(),
                theater.getMaxRows(),
                theater.getMaxColumns()
        );
    }
}
