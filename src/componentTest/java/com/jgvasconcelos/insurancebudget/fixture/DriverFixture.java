package com.jgvasconcelos.insurancebudget.fixture;

import com.jgvasconcelos.insurancebudget.domain.model.Driver;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class DriverFixture {
    public static Driver createValidDriver() {
        return Driver.builder()
                .id(UUID.randomUUID().toString())
                .name("João da Silva")
                .document("07682973101")
                .birthdate(LocalDate.of(1987, 6, 25))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static Driver createValidDriverWithoutId() {
        return Driver.builder()
                .name("João da Silva")
                .document("07682973101")
                .birthdate(LocalDate.of(1987, 6, 25))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }

    public static String createValidDriverId() {
        return UUID.randomUUID().toString();
    }

    public static String createValidDriverDocument() {
        return "07682973101";
    }

    public static String createValidNewDriverName() {
        return "José dos Santos";
    }
}
