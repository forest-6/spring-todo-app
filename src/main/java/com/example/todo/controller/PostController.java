package com.example.todo.controller;

import com.example.todo.common.ApiResponse;
import com.example.todo.domain.UserEntity;
import com.example.todo.dto.post.PostCreateRequest;
import com.example.todo.dto.post.PostResponse;
import com.example.todo.dto.post.PostUpdateRequest;
import com.example.todo.service.PostService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<Void> createPost(
            @RequestBody PostCreateRequest request,
            @AuthenticationPrincipal UserEntity user) {
        service.createPost(new PostCreateRequest(request.getTitle(), request.getContent(), user.getId()));

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
    public ApiResponse<PostResponse> getPost(@PathVariable Long id) {
        return new ApiResponse<>(
                "SUCCESS",
                "조회 성공",
                service.getPost(id)
        );
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updatePost(
            @PathVariable Long id,
            @RequestBody PostUpdateRequest request,
            @AuthenticationPrincipal UserEntity user
    ) {
        service.updatePost(new PostUpdateRequest(id, request.getTitle(), request.getContent()), user.getId());

        return new ApiResponse<>(
                "SUCCESS",
                "수정 성공",
                null
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserEntity user) {
        service.deletePost(id, user.getId());

        return new ApiResponse<>(
                "SUCCESS",
                "삭제 성공",
                null
        );
    }
}