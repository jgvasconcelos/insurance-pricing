package com.jgvasconcelos.insurancebudget.application.web.dto.response;

import com.jgvasconcelos.insurancebudget.domain.model.CarDriver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CarDriverResponseDto {
    private String id;
    private DriverResponseDto driver;
    private CarResponseDto car;
    private Boolean isMainDriver;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CarDriverResponseDto fromModel(CarDriver carDriver) {
        return CarDriverResponseDto.builder()
                .id(carDriver.getId())
                .driver(DriverResponseDto.fromModelWithoutNested(carDriver.getDriver()))
                .car(CarResponseDto.fromModelWihtoutNested(carDriver.getCar()))
                .isMainDriver(carDriver.getIsMainDriver())
                .createdAt(carDriver.getCreatedAt())
                .updatedAt(carDriver.getUpdatedAt())
                .build();
    }
}
