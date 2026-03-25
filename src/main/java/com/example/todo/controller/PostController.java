package com.example.todo.controller;

import com.example.todo.model.Post;
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
    public void createPost(@RequestBody Post post) {
        service.createPost(post);
    }

    @GetMapping
    public List<Post> getPosts() {
        return service.getAllPosts();
    }

    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {
        return service.getPost(id);
    }

    @PutMapping("/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody Post post) {
        post.setId(id);
        service.updatePost(post);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        service.deletePost(id);
    }
}