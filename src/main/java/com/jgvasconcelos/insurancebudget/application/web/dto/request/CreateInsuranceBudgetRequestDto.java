package com.jgvasconcelos.insurancebudget.application.web.dto.request;

import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.domain.model.InsuranceBudget;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInsuranceBudgetRequestDto {
    @NotBlank(message = "Driver id should not be null, empty or blank.")
    private String driverId;
    @NotBlank(message = "Car id should not be null, empty or blank.")
    private String carId;

    public InsuranceBudget toModel() {
        return InsuranceBudget.builder()
                .car(Car.builder().id(this.carId).build())
                .driver(Driver.builder().id(this.driverId).build())
                .build();
    }
}
