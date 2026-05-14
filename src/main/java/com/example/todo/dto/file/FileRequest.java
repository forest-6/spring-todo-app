package com.example.todo.dto.file;

public record FileRequest(
        Long postId,
        String originName,
        String storedName,
        String filePath,
        Long fileSize
) {
}
