package com.example.todo.exception;

import com.example.todo.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // 모든 Controller에서 발생한 예외를 전역에서 잡음
public class GlobalExceptionHandler {

    // 우선순위: 구체적 예외 -> 일반 예외
    @ExceptionHandler(PostNotFoundException.class) // PostNotFoundException 발생하면 이 메서드 실행
    public ResponseEntity<ApiResponse<Void>> handlePostNotFound(PostNotFoundException e /*발생한 예외 객체*/) {
        // HTTP 응답 전체를 직접 만드는 객체
        return ResponseEntity.status(404).body(
                new ApiResponse<>(
                        "POST_NOT_FOUND",
                        e.getMessage(), // super로 넣은 메시지
                        null
                )
        );
    }

    @ExceptionHandler(Exception.class) // 모든 예외가 발생하면 이 메서드 실행
    public ResponseEntity<ApiResponse<Void>> handleException(Exception e) {
        return ResponseEntity.status(500).body(
                new ApiResponse<>(
                        "SERVER_ERR",
                        "서버 에러 발생",
                        null
                )
        );
    }
}
