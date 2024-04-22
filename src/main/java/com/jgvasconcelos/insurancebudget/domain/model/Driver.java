package com.jgvasconcelos.insurancebudget.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Driver {
    private String id;
    private String name;
    private String document;
    private LocalDate birthdate;
    private List<Accident> accidents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
