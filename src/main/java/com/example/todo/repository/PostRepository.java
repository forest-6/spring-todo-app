package com.example.todo.repository;

import com.example.todo.domain.Post;
import com.example.todo.dto.post.PostCreateRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(Post post) {
        String uuid = UUID.randomUUID().toString();
        String sql = "INSERT INTO posts (id, title, content) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, uuid, post.getTitle(), post.getContent());
    }

    public List<Post> findAll() {
        String sql = "SELECT * FROM posts";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Post(rs.getString("id"), rs.getString("title"), rs.getString("content")));
    }

    public Post findById(String id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new Post(rs.getString("id"), rs.getString("title"), rs.getString("content")), id);
    }

    public void update(Post post) {
        String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getId());
    }

    public void delete(String id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
