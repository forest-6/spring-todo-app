package com.example.todo.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private String loginId;
    private String password;
}
