package com.example.todo.controller;

import com.example.todo.common.ApiResponse;
import com.example.todo.dto.user.UserCreateRequest;
import com.example.todo.dto.user.UserRequest;
import com.example.todo.dto.user.UserResponse;
import com.example.todo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/exists-by-id")
    public ApiResponse<Boolean> existsById(@RequestParam("username") String username) {
        boolean exists = service.existsById(username);

        return exists
                ? new ApiResponse<>("EXIST", "이미 사용 중인 아이디입니다.", true)
                : new ApiResponse<>("NOT_EXIST", "사용 가능한 아이디입니다.", false);
    }

    @PostMapping("/signUp")
    public ApiResponse<Void> signUp(@RequestBody UserCreateRequest request) {
        service.signUp(request);

        return new ApiResponse<>(
                "SUCCESS",
                "회원가입 성공",
                null
        );
    }

    @PostMapping("/signIn")
    public ApiResponse<UserResponse> signIn(@RequestBody UserRequest request) {
        return new ApiResponse<>(
                "SUCCESS",
                "로그인 성공",
                service.signIn(request)
        );
    }
}
