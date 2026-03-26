package com.example.todo.controller;

import com.example.todo.common.ApiResponse;
import com.example.todo.dto.post.PostCreateRequest;
import com.example.todo.dto.post.PostResponse;
import com.example.todo.dto.post.PostUpdateRequest;
import com.example.todo.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping
    public void createPost(@RequestBody PostCreateRequest request) {
        service.createPost(request);
    }

    @GetMapping
    public ApiResponse<List<PostResponse>> getPosts() {
        return new ApiResponse<>(
                "SUCCESS",
                "조회성공",
                service.getAllPosts()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<PostResponse> getPost(@PathVariable Long id) {
        return new ApiResponse<>(
                "SUCCESS",
                "조회 성공",
                service.getPost(id)
        );
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        service.updatePost(id, request);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        service.deletePost(id);
    }
}