package com.jgvasconcelos.insurancebudget.resources.repository.cardriver.entity;

import com.jgvasconcelos.insurancebudget.domain.model.CarDriver;
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

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "CarDriver")
@Table(name = "car_drivers")
public class CarDriverEntity {
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
    @Column(name = "is_main_driver", nullable = false)
    private Boolean isMainDriver;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public CarDriver toModel() {
        return CarDriver.builder()
                .id(this.id)
                .driver(this.driver.toModelWithoutNested())
                .car(this.car.toModelWithoutNested())
                .isMainDriver(this.isMainDriver)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public static CarDriverEntity fromModel(CarDriver carDriver) {
        return CarDriverEntity.builder()
                .id(carDriver.getId())
                .driver(DriverEntity.fromModel(carDriver.getDriver()))
                .car(CarEntity.fromModel(carDriver.getCar()))
                .isMainDriver(carDriver.getIsMainDriver())
                .createdAt(carDriver.getCreatedAt())
                .updatedAt(carDriver.getUpdatedAt())
                .build();
    }
}
