package com.grupp5.sf2api.request.theater;

public record UpdateTheaterRequest(
    String name,
    int totalSeats,
    int maxRows,
    int maxColumns
) {
}
