package com.jgvasconcelos.insurancebudget.fixture;

import com.jgvasconcelos.insurancebudget.domain.model.Car;

import java.time.LocalDateTime;
import java.util.UUID;

public class CarFixture {
    public static Car createValidCar() {
        return Car.builder()
                .id(UUID.randomUUID().toString())
                .model("HB20X Diamond")
                .manufacturer("Hyundai")
                .year(2020)
                .fipeValue(73500.00F)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static Car createValidCarWithoutId() {
        return Car.builder()
                .model("HB20X Diamond")
                .manufacturer("Hyundai")
                .year(2020)
                .fipeValue(73500.00F)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static String createValidCarId() {
        return UUID.randomUUID().toString();
    }

    public static Car createValidCarWithUpdatedFipeValue(Car carToUpdateFipeValue, Float newFipeValue) {
        return Car.builder()
                .id(carToUpdateFipeValue.getId())
                .model(carToUpdateFipeValue.getModel())
                .manufacturer(carToUpdateFipeValue.getManufacturer())
                .year(carToUpdateFipeValue.getYear())
                .fipeValue(newFipeValue)
                .createdAt(carToUpdateFipeValue.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static Float createvalidNewFipeValue() {
        return 80000.00F;
    }
}
