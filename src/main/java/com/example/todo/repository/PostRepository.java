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

    public List<PostEntity> findAllPaged(int pageSize, int pageIndex) {
        // rownum 방식
        // "SELECT * from " +
        // "(SELECT @rownum := @rownum + 1 as rn, id, title, content, creator_id, created_at, updated_at " +
        // "FROM posts, (SELECT @rownum := 0) as rowcolumn order by created_at DESC) " +
        // "WHERE rn > 0 AND rn <= 10;";

        int offset = (pageIndex - 1) * pageSize;
        int start = offset + 1;
        int end = offset + pageSize;

        String sql = "SELECT * FROM (" +
                "   SELECT ROW_NUMBER() OVER (ORDER BY created_at DESC, id DESC) AS rn, p.* " +
                "   FROM posts p" +
                ") AS t " +
                "WHERE rn BETWEEN ? AND ?";

        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(PostEntity.class),
                start, end);
    }

    public int getTotalCount() {
        String sql = "SELECT COUNT(*) FROM posts";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);

        return (count != null) ? count : 0;
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
