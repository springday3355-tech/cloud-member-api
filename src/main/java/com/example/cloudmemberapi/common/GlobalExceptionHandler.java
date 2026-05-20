package com.example.cloudmemberapi.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler { // 종합병원 응급실

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        log.error("[API - ERROR] 서버 내부 예외 발생: ", e);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("서버 내부에서 에러가 발생했습니다: " + e.getMessage());
    }
}
