package com.example.todo.repository;

import com.example.todo.domain.PostEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(PostEntity post) {
        String sql = "INSERT INTO posts (title, content) VALUES (?, ?)";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent());
    }

    public List<PostEntity> findAll() {
        String sql = "SELECT * FROM posts";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new PostEntity(rs.getLong("id"), rs.getString("title"), rs.getString("content")));
    }

    public Optional<PostEntity> findById(Long id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new PostEntity(rs.getLong("id"), rs.getString("title"), rs.getString("content")), id));
    }

    public void update(PostEntity post) {
        String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, post.getTitle(), post.getContent(), post.getId());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
