package com.example.todo.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PostController {

    private final JdbcTemplate jdbcTemplate;

    public PostController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/posts")
    public void createPost(@RequestBody Map<String, String> body) {
        String sql = "INSERT INTO posts (title, content) VALUES (?,?)";
        jdbcTemplate.update(sql, body.get("title"), body.get("content"));
    }

    @GetMapping("/posts")
    public List<Map<String, Object>> getPosts() {
        String sql = "SELECT * FROM posts";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/posts/{id}")
    public Map<String, Object> getPost(@PathVariable Long id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        return jdbcTemplate.queryForMap(sql, id);
    }

    @PutMapping("/posts/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, body.get("title"), body.get("content"), id);
    }

    @DeleteMapping("/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}

