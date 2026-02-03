package com.grupp5.sf2api.dtos.user;

import com.grupp5.sf2api.models.user.User;

import java.util.UUID;

public record DeletedUserDto(
        UUID userid,
        String email
) {
    public static DeletedUserDto from(User user) {
        return new DeletedUserDto(
                user.getUserId(),
                user.getEmail()
        );
    }
}
