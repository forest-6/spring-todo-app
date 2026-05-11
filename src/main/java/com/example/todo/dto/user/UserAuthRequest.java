package com.example.todo.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthRequest {
    private String username;
    private String password;
}
