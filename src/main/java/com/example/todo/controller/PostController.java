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
    public ApiResponse<Void> createPost(@RequestBody PostCreateRequest request) {
        service.createPost(request);

        return new ApiResponse<>(
                "SUCCESS",
                "등록 성공",
                null
        );
    }

    @GetMapping
    public ApiResponse<List<PostResponse>> getPosts() {
        return new ApiResponse<>(
                "SUCCESS",
                "조회 성공",
                service.getAllPosts()
        );
    }

    @GetMapping("/{id}")
    public ApiResponse<PostResponse> getPost(@PathVariable String id) {
        return new ApiResponse<>(
                "SUCCESS",
                "조회 성공",
                service.getPost(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updatePost(@PathVariable String id, @RequestBody PostUpdateRequest request) {
        service.updatePost(id, request);

        return new ApiResponse<>(
                "SUCCESS",
                "수정 성공",
                null
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePost(@PathVariable String id) {
        service.deletePost(id);

        return new ApiResponse<>(
                "SUCCESS",
                "삭제 성공",
                null
        );
    }
}