package com.example.todo.exception.user;

import com.example.todo.exception.ClientErrorException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends ClientErrorException {
    public UserAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "User already exists.");
    }

    public UserAlreadyExistsException(String username) {
        super(HttpStatus.CONFLICT, "User with username" + username + " already exists.");
    }
}
