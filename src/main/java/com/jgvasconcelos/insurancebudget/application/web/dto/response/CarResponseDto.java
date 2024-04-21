package com.jgvasconcelos.insurancebudget.application.web.dto.response;

import com.jgvasconcelos.insurancebudget.domain.model.car.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CarResponseDto {
    private String id;
    private String model;
    private String manufacturer;
    private Integer year;
    private Float fipeValue;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CarResponseDto fromModel(Car car) {
        return CarResponseDto.builder()
                .id(car.getId())
                .model(car.getModel())
                .manufacturer(car.getManufacturer())
                .year(car.getYear())
                .fipeValue(car.getFipeValue())
                .createdAt(car.getCreatedAt())
                .updatedAt(car.getUpdatedAt())
                .build();
    }
}
