package com.jgvasconcelos.insurancebudget.application.web.dto.request;

import com.jgvasconcelos.insurancebudget.domain.model.Accident;
import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccidentRequestDto {
    @NotBlank(message = "Driver id should not be null, empty or blank.")
    private String driverId;
    @NotBlank(message = "Car id should not be null, empty or blank.")
    private String carId;
    @NotNull(message = "Accident date should not be null.")
    private LocalDate accidentDate;

    public Accident toModel() {
        return Accident.builder()
                .car(Car.builder().id(this.carId).build())
                .driver(Driver.builder().id(this.driverId).build())
                .accidentDate(this.getAccidentDate())
                .build();
    }
}
