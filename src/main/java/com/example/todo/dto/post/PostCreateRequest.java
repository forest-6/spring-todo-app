package com.example.todo.dto.post;

public record PostCreateRequest(
        String title,
        String content,
        Long creatorId
) {
}