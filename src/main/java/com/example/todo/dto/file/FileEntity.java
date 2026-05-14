package com.example.todo.dto.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class FileEntity {
    private Long postId;
    private String originName;
    private String storedName;
    private String filePath;
    private Long fileSize;
}
