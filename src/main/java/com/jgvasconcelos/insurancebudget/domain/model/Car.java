package com.jgvasconcelos.insurancebudget.domain.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Car {
    private String id;
    private String model;
    private String manufacturer;
    private Integer year;
    private BigDecimal fipeValue;
    private List<Accident> accidents;
    private List<CarDriver> carDrivers;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
