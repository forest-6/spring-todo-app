package com.example.todo.dto.user;

import com.example.todo.domain.UserEntity;

public record User(
        Long id,
        String username,
        String createdAt
) {

    public static User from(UserEntity userEntity){
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getCreated_at()
        );
    }
}
