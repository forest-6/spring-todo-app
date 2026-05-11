package com.example.todo.service;

import com.example.todo.domain.UserEntity;
import com.example.todo.dto.user.User;
import com.example.todo.dto.user.UserTokenResponse;
import com.example.todo.exception.user.UserAlreadyExistsException;
import com.example.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final JwtService jwtService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, JwtService jwtService) {
        this.repository = repository;
        this.jwtService = jwtService;
    }

    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다: " + username));
    }

    public User signUp(String username, String password) {
        repository
                .findByUsername(username)
                .ifPresent(user -> { throw new UserAlreadyExistsException(); });

        var userEntity = repository.signUp(username, passwordEncoder.encode(password));
        return User.from(userEntity);
    }

    public UserTokenResponse signIn(String username, String password) {
        UserDetails user = loadUserByUsername(username);
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtService.generateAccessToken(user);
        return new UserTokenResponse(accessToken);
    }
}
