package com.grupp5.sf2api.services.theater;

import com.grupp5.sf2api.models.theater.Theater;
import com.grupp5.sf2api.request.theater.UpdateTheaterRequest;

import java.util.List;
import java.util.UUID;

public interface ITheaterService {
    Theater createTheater(Theater theater);
    Theater updateTheater(UUID theaterId, UpdateTheaterRequest request);
    List<Theater> getAllTheaters();
    Theater deleteTheater(UUID theaterId);
}
