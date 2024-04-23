package com.jgvasconcelos.insurancebudget.fixture;

import com.jgvasconcelos.insurancebudget.domain.model.InsuranceBudget;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class InsuranceBudgetFixture {
    public static InsuranceBudget createValidInsuranceBudget() {
        return InsuranceBudget.builder()
                .id(UUID.randomUUID().toString())
                .car(CarFixture.createValidCar())
                .driver(DriverFixture.createValidDriver())
                .isActive(true)
                .budgetAmount(new BigDecimal("80000.00"))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
