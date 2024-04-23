package com.jgvasconcelos.insurancebudget.application.web.dto.request;

import com.jgvasconcelos.insurancebudget.application.web.validator.NullOrNotBlank;
import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.domain.model.InsuranceBudget;

public class UpdateInsuranceBudgetRequestDto {
    @NullOrNotBlank(message = "Car id should not be empty or blank.")
    private String carId;
    @NullOrNotBlank(message = "Car id should not be empty or blank.")
    private String driverId;

    public InsuranceBudget toModel() {
        return InsuranceBudget.builder()
                .car(Car.builder().id(carId).build())
                .driver(Driver.builder().id(driverId).build())
                .build();
    }
}
