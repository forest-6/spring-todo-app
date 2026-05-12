package com.example.todo.dto.post;

public record PostUpdateRequest(
        Long id,
        String title,
        String content
) {
}
