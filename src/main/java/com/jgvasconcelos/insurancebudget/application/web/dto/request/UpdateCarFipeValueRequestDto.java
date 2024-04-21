package com.jgvasconcelos.insurancebudget.application.web.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCarFipeValueRequestDto {
    @NotNull(message = "Fipe Value should not be null.")
    @Positive(message = "Fipe Value should be a positive number.")
    private Float fipeValue;
}
