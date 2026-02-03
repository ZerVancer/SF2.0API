package com.grupp5.sf2api.request.user;

public record LoginUserRequest(
        String email,
        String password
) {
}
