package com.jgvasconcelos.insurancebudget.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Data
@Builder
public class InsuranceBudget {
    private String id;
    private Driver driver;
    private Car car;
    private Boolean isActive;
    private BigDecimal budgetAmount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private static final Integer RISK_AGE_LOWER_TRESHOLD = 18;
    private static final Integer RISK_AGE_UPPER_THRESHOLD = 25;

    private static final BigDecimal BASE_INSURANCE_PERCENTAGE = new BigDecimal("1.06");
    private static final BigDecimal DRIVER_IS_IN_HIGH_RISK_AGE =  new BigDecimal("0.02");
    private static final BigDecimal CAR_HAS_REGISTERED_ACCIDENT = new BigDecimal("0.02");
    private static final BigDecimal DRIVER_HAS_REGISTERED_ACCIDENT = new BigDecimal("0.02");

    public BigDecimal calculateInsuranceBudgetAmount() {
        BigDecimal fipeValue = this.car.getFipeValue();
        Integer driverAgeInYears = this.driver.calculateAgeInYears();

        BigDecimal finalPercentage = BASE_INSURANCE_PERCENTAGE;

        if (this.car.getAccidents() != null && !this.car.getAccidents().isEmpty()) {
            finalPercentage = finalPercentage.add(CAR_HAS_REGISTERED_ACCIDENT);
        }

        if (this.driver.getAccidents() != null && !this.driver.getAccidents().isEmpty()) {
            finalPercentage = finalPercentage.add(DRIVER_HAS_REGISTERED_ACCIDENT);
        }

        if (driverAgeInYears >= RISK_AGE_LOWER_TRESHOLD && driverAgeInYears <= RISK_AGE_UPPER_THRESHOLD) {
            finalPercentage = finalPercentage.add(DRIVER_IS_IN_HIGH_RISK_AGE);
        }

        return fipeValue.multiply(finalPercentage).setScale(2, RoundingMode.HALF_EVEN);
    }

    public void updateChangedValues(InsuranceBudget other) {
        if (other.getCar() != null && !this.getCar().getId().equals(other.getCar().getId())) this.car = other.getCar();
        if (other.getDriver() != null && !this.getDriver().getId().equals(other.getDriver().getId())) this.driver = other.getDriver();
    }
}
