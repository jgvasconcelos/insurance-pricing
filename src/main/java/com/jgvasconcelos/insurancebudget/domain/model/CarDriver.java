package com.jgvasconcelos.insurancebudget.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CarDriver {
    private String id;
    private Car car;
    private Driver driver;
    private Boolean isMainDriver;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
