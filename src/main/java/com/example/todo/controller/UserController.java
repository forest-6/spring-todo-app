package com.example.todo.controller;

import com.example.todo.dto.user.User;
import com.example.todo.dto.user.UserAuthRequest;
import com.example.todo.dto.user.UserTokenResponse;
import com.example.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }



    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody UserAuthRequest request) {
        var user = service.signUp(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserTokenResponse> signIn(@RequestBody UserAuthRequest request) {
        var token = service.signIn(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(token);
    }
}
