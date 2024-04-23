package com.jgvasconcelos.insurancebudget.fixture;

import com.jgvasconcelos.insurancebudget.domain.model.Accident;

import java.time.LocalDateTime;
import java.util.UUID;

public class AccidentFixture {
    public static Accident createValidAccident() {
        return Accident.builder()
                .id(UUID.randomUUID().toString())
                .car(CarFixture.createValidCar())
                .driver(DriverFixture.createValidDriver())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
