package com.example.todo.dto.post;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostResponse {

    private Long id;
    private String title;
    private String content;
}
