package com.example.todo.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public record PostCreateRequest(
        String title,
        String content,
        Long creatorId
) {
}