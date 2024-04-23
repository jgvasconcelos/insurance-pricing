package com.jgvasconcelos.insurancebudget.application.web.dto.request;

import com.jgvasconcelos.insurancebudget.domain.model.Car;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequestDto {
    @NotBlank(message = "Model should not be null, empty or blank.")
    private String model;
    @NotBlank(message = "Manufacturer should not be null, empty or blank.")
    private String manufacturer;
    @NotNull(message = "Year should not be null.")
    @Positive(message = "Year should be a positive number.")
    private Integer year;
    @NotNull(message = "Fipe Value should not be null.")
    @Positive(message = "Fipe Value should be a positive number.")
    private BigDecimal fipeValue;

    public Car toModel() {
        return Car.builder()
                .model(this.model)
                .manufacturer(this.manufacturer)
                .year(this.year)
                .fipeValue(this.fipeValue)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
