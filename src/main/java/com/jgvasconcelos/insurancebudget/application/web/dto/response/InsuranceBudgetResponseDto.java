package com.jgvasconcelos.insurancebudget.application.web.dto.response;

import com.jgvasconcelos.insurancebudget.domain.model.InsuranceBudget;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class InsuranceBudgetResponseDto {
    private String id;
    private DriverResponseDto driver;
    private CarResponseDto car;
    private BigDecimal budgetAmount;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static InsuranceBudgetResponseDto fromModel(InsuranceBudget insuranceBudget) {
        return InsuranceBudgetResponseDto.builder()
                .id(insuranceBudget.getId())
                .driver(DriverResponseDto.fromModelWithoutNested(insuranceBudget.getDriver()))
                .car(CarResponseDto.fromModelWihtoutNested(insuranceBudget.getCar()))
                .budgetAmount(insuranceBudget.getBudgetAmount())
                .isActive(insuranceBudget.getIsActive())
                .createdAt(insuranceBudget.getCreatedAt())
                .updatedAt(insuranceBudget.getUpdatedAt())
                .build();
    }
}
