package com.example.todo.controller;

import com.example.todo.domain.UserEntity;
import com.example.todo.dto.user.*;
import com.example.todo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        var user = service.signUp(request.username(), request.password());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserRefreshTokenResponse> signIn(@RequestBody UserAuthRequest request) {
        var response = service.signIn(request.username(), request.password());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<UserTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(service.refreshToken(request.refreshToken()));
    }

    @GetMapping("logout")
    public ResponseEntity<Void> logut(
            @AuthenticationPrincipal UserEntity user
    ) {
        service.logout(user);
        return ResponseEntity.ok().build();
    }
}
