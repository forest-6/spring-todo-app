package com.example.todo.repository;

import com.example.todo.model.Post;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Post post) {
        String sql = "INSERT INTO posts (title, content) VALUES (?, ?)";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent());
    }

    public List<Post> findAll() {
        String sql = "SELECT * FROM posts";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Post(rs.getLong("id"), rs.getString("title"), rs.getString("content")));
    }

    public Post findById(Long id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Post(rs.getLong("id"), rs.getString("title"), rs.getString("content")), id);
    }

    public void update(Post post) {
        String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
