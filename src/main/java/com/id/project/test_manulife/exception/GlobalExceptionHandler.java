package com.id.project.test_manulife.exception;

import com.id.project.test_manulife.dto.error.ErrorResponse;
import com.id.project.test_manulife.util.LoggingUtil;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. HTTP 400 - Bad Request (Validasi Input DTO Gagal)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        DateTime startTime = DateTime.now();
        String errorMessage = ex.getBindingResult().getFieldError() != null
                ? ex.getBindingResult().getFieldError().getDefaultMessage()
                : "Invalid request payload";

        LoggingUtil.logError("VALIDATION_ERROR", errorMessage, ex, startTime);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("invalid_request")
                .errorDescription(errorMessage)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // 2. HTTP 401 - Unauthorized (Gagal Kredensial / Auth)
    @ExceptionHandler(InvalidClientException.class)
    public ResponseEntity<ErrorResponse> handleInvalidClientException(InvalidClientException ex) {
        DateTime startTime = DateTime.now();
        LoggingUtil.logError("AUTH_ERROR", ex.getMessage(), ex, startTime);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("invalid_client")
                .errorDescription(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }

    // 3. HTTP 500 - Internal Server Error (Error Server Tak Terduga)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        DateTime startTime = DateTime.now();
        LoggingUtil.logError("SERVER_ERROR", ex.getMessage(), ex, startTime);

        ErrorResponse errorResponse = ErrorResponse.builder()
                .error("server_error")
                .errorDescription("An unexpected error occurred on the server")
                .build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}