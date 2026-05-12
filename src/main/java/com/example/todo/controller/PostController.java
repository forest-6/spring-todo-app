package com.example.todo.controller;

import com.example.todo.dto.common.PagingResult;
import com.example.todo.domain.UserEntity;
import com.example.todo.dto.post.PostCreateRequest;
import com.example.todo.dto.post.PostResponse;
import com.example.todo.dto.post.PostSearchRequest;
import com.example.todo.dto.post.PostUpdateRequest;
import com.example.todo.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createPost(
            @RequestBody PostCreateRequest request,
            @AuthenticationPrincipal UserEntity user) {
        service.createPost(new PostCreateRequest(request.title(), request.content(), user.getId()));

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<PagingResult<PostResponse>> getPosts(
            @ModelAttribute PostSearchRequest request
    ) {
        return ResponseEntity.ok(service.getPostPage(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        return ResponseEntity.ok(service.getPost(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePost(
            @PathVariable Long id,
            @RequestBody PostUpdateRequest request,
            @AuthenticationPrincipal UserEntity user
    ) {
        service.updatePost(new PostUpdateRequest(id, request.title(), request.content()), user.getId());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(
            @PathVariable Long id,
            @AuthenticationPrincipal UserEntity user) {
        service.deletePost(id, user.getId());

        return ResponseEntity.ok().build();
    }
}