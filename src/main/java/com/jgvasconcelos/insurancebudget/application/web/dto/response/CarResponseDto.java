package com.jgvasconcelos.insurancebudget.application.web.dto.response;

import com.jgvasconcelos.insurancebudget.domain.model.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CarResponseDto {
    private String id;
    private String model;
    private String manufacturer;
    private Integer year;
    private BigDecimal fipeValue;
    private List<AccidentResponseDto> accidents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CarResponseDto fromModel(Car car) {
        CarResponseDto carResponseDto = CarResponseDto.builder()
                .id(car.getId())
                .model(car.getModel())
                .manufacturer(car.getManufacturer())
                .year(car.getYear())
                .fipeValue(car.getFipeValue())
                .createdAt(car.getCreatedAt())
                .updatedAt(car.getUpdatedAt())
                .build();

        if (car.getAccidents() != null) {
            carResponseDto.accidents = car.getAccidents().stream().map(AccidentResponseDto::fromModel).toList();
        }

        return carResponseDto;
    }

    public static CarResponseDto fromModelWihtoutNested(Car car) {

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
