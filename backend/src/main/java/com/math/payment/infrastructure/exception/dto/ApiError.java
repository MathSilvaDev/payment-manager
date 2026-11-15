package com.math.payment.infrastructure.exception.dto;

import org.springframework.http.HttpStatus;

import java.time.Instant;

public record ApiError(
        String apiPath,
        HttpStatus errorCode,
        String errorMessage,
        Instant errorTime
) {
    public ApiError(String apiPath ,HttpStatus errorCode, String errorMessage){
        this(apiPath, errorCode, errorMessage, Instant.now());
    }
}
