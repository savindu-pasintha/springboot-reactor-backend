package com.example.springbootreactorbackend.exception;

import com.example.springbootreactorbackend.common.FormattedApiResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.eclipse.angus.mail.util.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleValidationExceptions(Exception ex, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        if (ex instanceof MethodArgumentNotValidException) {
            // Handle validation errors
            Map<String, String> errors = new HashMap<>();
            ((MethodArgumentNotValidException) ex).getBindingResult()
                    .getFieldErrors()
                    .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            body.put("errors", errors);
        } else if (ex instanceof HttpMessageNotReadableException) {
            // Handle enum conversion errors
            body.put("error", "Invalid enum value. Check your input format");
        }

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}