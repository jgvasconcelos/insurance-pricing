package com.jgvasconcelos.insurancebudget.application.web.dto.request;

import com.jgvasconcelos.insurancebudget.application.web.validator.NullOrNotBlank;
import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.domain.model.InsuranceBudget;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateInsuranceBudgetRequestDto {
    @NullOrNotBlank(message = "Car id should not be empty or blank.")
    private String carId;
    @NullOrNotBlank(message = "Car id should not be empty or blank.")
    private String driverId;

    public InsuranceBudget toModel() {
        InsuranceBudget insuranceBudget = InsuranceBudget.builder().build();

        if (carId != null) {
            insuranceBudget.setCar(Car.builder().id(carId).build());
        }

        if (driverId != null) {
            insuranceBudget.setDriver(Driver.builder().id(driverId).build());
        }

        return insuranceBudget;
    }
}
