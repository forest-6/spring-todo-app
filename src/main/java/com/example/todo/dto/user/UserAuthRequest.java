package com.example.todo.dto.user;

public record UserAuthRequest(
        String username,
        String password
) {
}
