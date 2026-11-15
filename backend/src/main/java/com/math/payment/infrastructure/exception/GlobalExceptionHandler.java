package com.math.payment.infrastructure.exception;

import com.math.payment.infrastructure.exception.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiError> responseApiError(String apiPath, HttpStatus status,
                                                      String errorMessage){
        return ResponseEntity
                .status(status)
                .body(new ApiError(apiPath, status, errorMessage));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handlerRuntimeException(RuntimeException exception,
                                                            WebRequest webRequest){
        return responseApiError(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                "ERROR NOT HANDLED");
    }
}
