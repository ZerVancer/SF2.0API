package com.grupp5.sf2api.dtos.theater;

import com.grupp5.sf2api.models.theater.Theater;

import java.util.UUID;

public record DeleteTheaterDto(
        UUID theaterId,
        String name,
        int totalSeats,
        int maxRows,
        int maxColumns
) {
    public static DeleteTheaterDto from(Theater theater) {
        return new DeleteTheaterDto(
                theater.getTheaterId(),
                theater.getName(),
                theater.getTotalSeats(),
                theater.getMaxRows(),
                theater.getMaxColumns()
        );
    }
}
