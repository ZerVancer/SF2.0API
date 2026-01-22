package com.grupp5.sf2api.request.user;

public record UpdateUserRequest(
        String email,
        String password
) {
}
