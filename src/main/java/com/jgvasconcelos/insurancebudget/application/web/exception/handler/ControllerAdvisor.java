package com.jgvasconcelos.insurancebudget.application.web.exception.handler;

import com.jgvasconcelos.insurancebudget.application.web.exception.ResourceNotFoundException;
import com.jgvasconcelos.insurancebudget.application.web.exception.dto.ApiErrorResponseDto;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ApiErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {
        ApiErrorResponseDto responseBody = ApiErrorResponseDto.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(resourceNotFoundException.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseBody);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiErrorResponseDto> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        for (FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
            errorMessageBuilder
                    .append("Error on field [")
                    .append(fieldError.getField())
                    .append("]. Error message: ")
                    .append(fieldError.getDefaultMessage())
                    .append(" ");
        }

        ApiErrorResponseDto responseBody = ApiErrorResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(errorMessageBuilder.toString())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ApiErrorResponseDto> constraintViolationExceptionHandler(ConstraintViolationException constraintViolationException) {
        StringBuilder errorMessageBuilder = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : constraintViolationException.getConstraintViolations()) {
            errorMessageBuilder
                    .append("Constraint violation :")
                    .append(constraintViolation.getMessage())
                    .append(" ");
        }

        ApiErrorResponseDto responseBody = ApiErrorResponseDto.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(errorMessageBuilder.toString())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
