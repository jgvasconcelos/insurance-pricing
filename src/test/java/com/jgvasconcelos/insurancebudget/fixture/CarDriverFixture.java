package com.jgvasconcelos.insurancebudget.fixture;

import com.jgvasconcelos.insurancebudget.domain.model.CarDriver;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarDriverFixture {
    public static CarDriver createValidCarDriver() {
        return CarDriver.builder()
                .id(UUID.randomUUID().toString())
                .car(CarFixture.createValidCar())
                .driver(DriverFixture.createValidDriver())
                .isMainDriver(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
