package com.grupp5.sf2api.dtos.user;

import com.grupp5.sf2api.models.tickets.Ticket;
import com.grupp5.sf2api.models.user.User;

import java.util.List;
import java.util.UUID;

public record UserAndTicketsDto(
        UUID userId,
        String email,
        List<Ticket> tickets
) {
    public static UserAndTicketsDto from(User user) {
        return new UserAndTicketsDto(
                user.getUserId(),
                user.getEmail(),
                user.getTickets()
        );
    }
}
