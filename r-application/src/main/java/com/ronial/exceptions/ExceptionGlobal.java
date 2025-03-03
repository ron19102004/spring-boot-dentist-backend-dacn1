package com.ronial.exceptions;

import com.ronial.models.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionGlobal {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleException(Exception e) {
        System.out.println(e.getMessage());
        return ResponseEntity.ok(ApiResponse.<Object>builder()
                .message(e.getMessage())
                .data(e)
                .build());
    }
}
