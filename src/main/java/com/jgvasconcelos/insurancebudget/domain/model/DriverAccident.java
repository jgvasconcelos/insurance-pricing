package com.jgvasconcelos.insurancebudget.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class DriverAccident {
    private String id;
    private Car car;
    private LocalDate accidentDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
