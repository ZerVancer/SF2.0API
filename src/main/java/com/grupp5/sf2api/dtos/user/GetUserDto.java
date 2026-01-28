package com.grupp5.sf2api.dtos.user;

import com.grupp5.sf2api.models.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record GetUserDto(
        UUID userId,
        String email,
        LocalDateTime createdAt
) {
    public static GetUserDto from(User user) {
        return new GetUserDto(
                user.getUserId(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
