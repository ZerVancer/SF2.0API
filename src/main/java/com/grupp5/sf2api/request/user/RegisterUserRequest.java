package com.grupp5.sf2api.request.user;

public record RegisterUserRequest(
        String email,
        String password
) {
}
