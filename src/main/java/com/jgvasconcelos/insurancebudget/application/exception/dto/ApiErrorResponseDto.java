package com.jgvasconcelos.insurancebudget.application.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApiErrorResponseDto {
    private Integer statusCode;
    private String message;
}
