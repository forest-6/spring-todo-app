package com.example.todo.service;

import com.example.todo.dto.user.UserCreateRequest;
import com.example.todo.dto.user.UserRequest;
import com.example.todo.dto.user.UserResponse;
import com.example.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean idCheck(String loginId) {
        return repository.idCheck(loginId);
    }

    public void signUp(UserCreateRequest request) {
        if(repository.idCheck(request.getLoginId())){
            throw new IllegalStateException("이미 사용 중인 아이디입니다.");
        }

        repository.signUp(request);
    }

    public UserResponse signIn(UserRequest request) {
        return repository.signIn(request);
    }
}
