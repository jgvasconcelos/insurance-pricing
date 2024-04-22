package com.jgvasconcelos.insurancebudget.resources.repository.accident.entity;

import com.jgvasconcelos.insurancebudget.domain.model.Accident;
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

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Accident")
@Table(name = "accidents")
public class AccidentEntity {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    private CarEntity car;
    @Column(name = "accident_date", nullable = false)
    private LocalDate accidentDate;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Accident toModel() {
        return Accident.builder()
                .id(this.id)
                .driver(this.driver.toModelWithoutNested())
                .car(this.car.toModelWithoutNested())
                .accidentDate(this.accidentDate)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public static AccidentEntity fromModel(Accident accident) {
        return AccidentEntity.builder()
                .id(accident.getId())
                .driver(DriverEntity.fromModel(accident.getDriver()))
                .car(CarEntity.fromModel(accident.getCar()))
                .accidentDate(accident.getAccidentDate())
                .createdAt(accident.getCreatedAt())
                .updatedAt(accident.getUpdatedAt())
                .build();
    }
}
