package com.jgvasconcelos.insurancebudget.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Car {
    private String id;
    private String model;
    private String manufacturer;
    private Integer year;
    private Float fipeValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
