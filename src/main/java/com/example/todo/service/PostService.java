package com.example.todo.service;

import com.example.todo.model.Post;
import com.example.todo.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public void createPost(Post post) {
        repository.save(post);
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public Post getPost(Long id) {
        return repository.findById(id);
    }

    public void updatePost(Post post) {
        repository.update(post);
    }

    public void deletePost(Long id) {
        repository.delete(id);
    }
}