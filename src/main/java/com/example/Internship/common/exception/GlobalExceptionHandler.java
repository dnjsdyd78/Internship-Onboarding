package com.example.Internship.common.exception;

import com.example.Internship.domain.apipayload.dto.ApiResponse;
import com.example.Internship.domain.apipayload.dto.BaseCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<String>> handleCustomException(ApiException e) {

        BaseCode errorCode = e.getErrorCode();
        return handleCustomInternal(errorCode);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleException(Exception e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    private ResponseEntity<ApiResponse<String>> handleCustomInternal(BaseCode errorCode) {

        return ResponseEntity.status(errorCode.getReasonHttpStatus().getHttpStatus())
                .body(ApiResponse.onFailure(errorCode));
    }
}