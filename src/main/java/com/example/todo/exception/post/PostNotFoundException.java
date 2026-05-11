package com.example.todo.exception.post;

import com.example.todo.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class PostNotFoundException extends ClientErrorException {

    public PostNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Post not found.");
    }

    public PostNotFoundException(Long postId) {
        super(HttpStatus.NOT_FOUND, "Post with postId" + postId + " not found.");
    }
}