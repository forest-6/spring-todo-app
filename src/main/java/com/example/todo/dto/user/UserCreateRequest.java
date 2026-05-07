package com.example.todo.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequest {
    private String loginId;
    private String password;
    private String name;
}
