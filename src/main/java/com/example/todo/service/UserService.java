package com.example.todo.service;

import com.example.todo.dto.user.UserCreateRequest;
import com.example.todo.dto.user.UserRequest;
import com.example.todo.dto.user.UserResponse;
import com.example.todo.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean existsById(String username) {
        return repository.findByUsername(username) == null ? false : true;
    }

    public void signUp(UserCreateRequest request) {
        if (repository.findByUsername(request.getLoginId()) != null) {
            throw new IllegalStateException("이미 사용 중인 아이디입니다.");
        }

        repository.signUp(request);
    }

    public UserResponse signIn(UserRequest request) {
        return repository.signIn(request);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
    }
}
