package com.example.todo.repository;

import com.example.todo.domain.PostEntity;
import com.example.todo.dto.post.PostCreateRequest;
import com.example.todo.dto.post.PostUpdateRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM posts WHERE id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    public void save(PostCreateRequest request) {
        String sql = "INSERT INTO posts (title, content, creator_id) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.title(), request.content(), request.creatorId());
    }

    public List<PostEntity> findAll() {
        String sql = "SELECT * FROM posts";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(PostEntity.class));
    }


    public Optional<PostEntity> findById(Long id) {
        String sql = "SELECT * FROM posts WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                new BeanPropertyRowMapper<>(PostEntity.class),
                id));
    }

    public void update(PostUpdateRequest request) {
        String sql = "UPDATE posts SET title = ?, content = ? WHERE id = ?";
        jdbcTemplate.update(sql, request.title(), request.content(), request.id());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM posts WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
