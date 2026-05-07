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

    @GetMapping("/id-check")
    public ApiResponse<Boolean> idCheck(@RequestParam("loginId") String loginId) {
        boolean exists = service.idCheck(loginId);

        ApiResponse<Boolean> response = exists
                ? new ApiResponse<>("EXIST", "이미 사용 중인 아이디입니다.", true)
                : new ApiResponse<>("NOT_EXIST", "사용 가능한 아이디입니다.", false);

        return response;
    }

    @PostMapping
    public ApiResponse<Void> signUp(@RequestBody UserCreateRequest request) {
        service.signUp(request);

        return new ApiResponse<>(
                "SUCCESS",
                "회원가입 성공",
                null
        );
    }

    @PostMapping
    public ApiResponse<UserResponse> signIn(@RequestBody UserRequest request) {
        return new ApiResponse<>(
                "SUCCESS",
                "로그인 성공",
                service.signIn(request)
        );
    }
}
