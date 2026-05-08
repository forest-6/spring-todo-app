package com.example.todo.repository;

import com.example.todo.domain.UserEntity;
import com.example.todo.dto.user.UserCreateRequest;
import com.example.todo.dto.user.UserRequest;
import com.example.todo.dto.user.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<UserEntity> findByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM users WHERE username = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new UserEntity(
                rs.getLong("id"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("created_at")
        ), username));
    }

    public void signUp(UserCreateRequest request) {
        String sql = "INSERT INTO users (username, password, name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.getLoginId(), request.getPassword(), request.getName());
    }

    public UserResponse signIn(UserRequest request) {
        String sql = "SELECET * FROM users WHERE username = ?, password = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new UserResponse(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("created_at")
                ),
                request.getLoginId(),
                request.getPassword()
        );
    }
}
