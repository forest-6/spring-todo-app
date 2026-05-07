package com.example.todo.repository;
import com.example.todo.dto.user.UserCreateRequest;
import com.example.todo.dto.user.UserRequest;
import com.example.todo.dto.user.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean idCheck(String loginId){
        String sql = "SELECT COUNT(*) FROM users WHERE login_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, loginId);
        return count != null && count > 0;
    }

    public void signUp(UserCreateRequest request) {
        String sql = "INSERT INTO users (login_id, password, name) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, request.getLoginId(), request.getPassword(), request.getName());
    }

    public UserResponse signIn(UserRequest request) {
        String sql = "SELECET * FROM users WHERE login_id = ?, password = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> new UserResponse(
                        rs.getLong("id"),
                        rs.getString("login_id"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("created_at")
                ),
                request.getLoginId(),
                request.getPassword()
        );
    }
}
