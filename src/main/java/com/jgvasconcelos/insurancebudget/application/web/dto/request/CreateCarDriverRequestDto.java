package com.jgvasconcelos.insurancebudget.application.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarDriverRequestDto {
    @NotBlank(message = "Driver id should not be null, empty or blank.")
    private String driverId;
    @NotBlank(message = "Car id should not be null, empty or blank.")
    private String carId;
    @NotNull(message = "Is Main Driver should not be null.")
    private Boolean isMainDriver;
}
