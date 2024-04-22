package com.jgvasconcelos.insurancebudget.resources.repository.car.entity;

import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.resources.repository.accident.entity.AccidentEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Car")
@Table(name = "cars")
public class CarEntity {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;
    @Column(name = "year", nullable = false)
    private Integer year;
    @Column(name = "fipe_value", nullable = false)
    private Float fipeValue;
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AccidentEntity> accidents;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Car toModel() {
        Car car = Car.builder()
                .id(this.id)
                .model(this.model)
                .manufacturer(this.manufacturer)
                .year(this.year)
                .fipeValue(this.fipeValue)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();

        if (this.accidents != null) {
            car.setAccidents(this.accidents.stream().map(AccidentEntity::toCarAccidentModel).toList());
        }

        return car;
    }

    public Car toModelWithoutNested() {
        return Car.builder()
                .id(this.id)
                .model(this.model)
                .manufacturer(this.manufacturer)
                .year(this.year)
                .fipeValue(this.fipeValue)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public static CarEntity fromModel(Car car) {
        CarEntity carEntity = CarEntity.builder()
                .id(car.getId())
                .model(car.getModel())
                .manufacturer(car.getManufacturer())
                .year(car.getYear())
                .fipeValue(car.getFipeValue())
                .createdAt(car.getCreatedAt())
                .updatedAt(car.getUpdatedAt())
                .build();

        if (car.getAccidents() != null) {
            carEntity.setAccidents(car.getAccidents().stream().map(AccidentEntity::fromCarAccidentModel).toList());
        }

        return carEntity;
    }
}
