package com.example.todo.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUpdateRequest {
    private Long id;
    private String title;
    private String content;
}
