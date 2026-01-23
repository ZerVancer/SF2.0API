package com.grupp5.sf2api.dtos.user;

import com.grupp5.sf2api.models.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public record RegisterUserDto(
        UUID userid,
        String email,
        LocalDateTime createdAt
) {
    public static RegisterUserDto from(User user) {
        return new RegisterUserDto(user.getUserId(), user.getEmail(), user.getCreatedAt());
    }
}
