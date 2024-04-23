package com.jgvasconcelos.insurancebudget.application.web.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarFipeValueRequestDto {
    @NotNull(message = "Fipe Value should not be null.")
    @Positive(message = "Fipe Value should be a positive number.")
    private BigDecimal fipeValue;
}
