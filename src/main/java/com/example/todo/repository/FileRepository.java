package com.example.todo.repository;

import com.example.todo.dto.file.FileEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepository {

    private final JdbcTemplate jdbcTemplate;

    public FileRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(FileEntity file) {
        String sql = "INSERT INTO post_files (post_id, origin_name, stored_name, file_path, file_size) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                file.getPostId(),
                file.getOriginName(),
                file.getStoredName(),
                file.getFilePath(),
                file.getFileSize()
        );
    }
}
