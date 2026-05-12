package com.example.todo.dto.post;

import com.example.todo.domain.PostEntity;

public record PostResponse(
        Long id,
        String title,
        String content,
        Long creatorId,
        String createdAt,
        String updatedAt) {
    public static PostResponse from(PostEntity postEntity) {
        return new PostResponse(
                postEntity.getId(),
                postEntity.getTitle(),
                postEntity.getContent(),
                postEntity.getCreator_id(),
                postEntity.getCreated_at(),
                postEntity.getUpdated_at()
        );
    }
}
