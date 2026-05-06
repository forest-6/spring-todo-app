package com.example.todo.service;

import com.example.todo.domain.Post;
import com.example.todo.dto.post.PostCreateRequest;
import com.example.todo.dto.post.PostResponse;
import com.example.todo.dto.post.PostUpdateRequest;
import com.example.todo.exception.PostNotFoundException;
import com.example.todo.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public void createPost(PostCreateRequest request) {
        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        repository.save(post);
    }

    public List<PostResponse> getAllPosts() {
        List<Post> posts = repository.findAll();

        return posts.stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getContent()
                )).toList();
    }

    public PostResponse getPost(String id) {
        Post post = repository.findById(id);

        if (post == null) {
            throw new PostNotFoundException();
        }

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent()
        );
    }

    public void updatePost(String id, PostUpdateRequest request) {
        Post post = new Post();
        post.setId(id);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        repository.update(post);
    }

    public void deletePost(String id) {
        repository.delete(id);
    }
}