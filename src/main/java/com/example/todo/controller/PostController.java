package com.example.todo.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
        jdbcTemplate.update(
                sql,
                body.get("title"),
                body.get("content")
        );
    }

    @GetMapping("/posts")
    public List<Map<String, Object>> getPosts() {
        String sql = "SELECT * FROM posts";
        return jdbcTemplate.queryForList(sql);
    }
}

