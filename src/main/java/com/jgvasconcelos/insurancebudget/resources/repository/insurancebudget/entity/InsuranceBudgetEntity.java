package com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.entity;

import com.jgvasconcelos.insurancebudget.domain.model.InsuranceBudget;
import com.jgvasconcelos.insurancebudget.resources.repository.car.entity.CarEntity;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.entity.DriverEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "InsuranceBudget")
@Table(name = "insurance_budgets")
public class InsuranceBudgetEntity {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarEntity car;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Column(name = "budget_amount", nullable = false)
    private BigDecimal budgetAmount;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public InsuranceBudget toModel() {
        return InsuranceBudget.builder()
                .id(this.id)
                .driver(this.driver.toModelWithoutNested())
                .car(this.car.toModelWithoutNested())
                .isActive(this.isActive)
                .budgetAmount(this.budgetAmount)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public static InsuranceBudgetEntity fromModel(InsuranceBudget insuranceBudget) {
        return InsuranceBudgetEntity.builder()
                .id(insuranceBudget.getId())
                .driver(DriverEntity.fromModel(insuranceBudget.getDriver()))
                .car(CarEntity.fromModel(insuranceBudget.getCar()))
                .isActive(insuranceBudget.getIsActive())
                .budgetAmount(insuranceBudget.getBudgetAmount())
                .createdAt(insuranceBudget.getCreatedAt())
                .updatedAt(insuranceBudget.getUpdatedAt())
                .build();
    }
}
