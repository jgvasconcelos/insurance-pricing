package com.jgvasconcelos.insurancebudget.application.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccidentRequestDto {
    @NotBlank(message = "Driver id should not be null, empty or blank.")
    private String driverId;
    @NotBlank(message = "Car id should not be null, empty or blank.")
    private String carId;
    @NotNull(message = "Accident date should not be null.")
    private LocalDate accidentDate;
}
