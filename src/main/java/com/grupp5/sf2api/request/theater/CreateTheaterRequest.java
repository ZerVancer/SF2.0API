package com.grupp5.sf2api.request.theater;

import java.util.UUID;

public record CreateTheaterRequest(
        String name,
        int totalSeats,
        int maxRows,
        int maxColumns,
        UUID cinemaId
) {
}
