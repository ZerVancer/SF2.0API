package com.grupp5.sf2api.dtos.theater;

import com.grupp5.sf2api.models.theater.Theater;

import java.util.UUID;

public record CreateTheaterDto(
        UUID theaterId,
        String name,
        int totalSeats,
        int maxRows,
        int maxColumns
) {
    public static CreateTheaterDto from(Theater theater) {
        return new CreateTheaterDto(
                theater.getTheaterId(),
                theater.getName(),
                theater.getTotalSeats(),
                theater.getMaxRows(),
                theater.getMaxColumns()
                );
    }
}
