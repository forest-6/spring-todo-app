package com.example.todo.dto.comment;

public record CommentCreateRequest(
        Long postId,
        Long parentId,
        String content
) {}
