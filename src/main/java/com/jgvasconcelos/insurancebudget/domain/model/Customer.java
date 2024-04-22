package com.jgvasconcelos.insurancebudget.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Customer {
    private String id;
    private String name;
    private Driver driver;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
