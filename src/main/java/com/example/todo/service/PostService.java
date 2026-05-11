package com.example.todo.service;

import com.example.todo.domain.PostEntity;
import com.example.todo.dto.post.PostCreateRequest;
import com.example.todo.dto.post.PostResponse;
import com.example.todo.dto.post.PostUpdateRequest;
import com.example.todo.exception.post.PostNotFoundException;
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
        PostEntity post = new PostEntity();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        repository.save(post);
    }

    public List<PostResponse> getAllPosts() {
        List<PostEntity> posts = repository.findAll();

        return posts.stream()
                .map(post -> new PostResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getContent()
                )).toList();
    }

    public PostResponse getPost(Long id) {
        PostEntity post = repository.findById(id).orElseThrow(() -> new PostNotFoundException(id));

        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent()
        );
    }

    public void updatePost(Long id, PostUpdateRequest request) {
        PostEntity post = new PostEntity();
        post.setId(id);
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());

        repository.update(post);
    }

    public void deletePost(Long id) {
        repository.delete(id);
    }
}