package com.jgvasconcelos.insurancebudget.application.web.dto.response;

import com.jgvasconcelos.insurancebudget.domain.model.Accident;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class AccidentResponseDto {
    private String id;
    private DriverResponseDto driver;
    private CarResponseDto car;
    private LocalDate accidentDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static AccidentResponseDto fromModel(Accident accident) {
        return AccidentResponseDto.builder()
                .id(accident.getId())
                .driver(DriverResponseDto.fromModelWithoutNested(accident.getDriver()))
                .car(CarResponseDto.fromModelWihtoutNested(accident.getCar()))
                .accidentDate(accident.getAccidentDate())
                .createdAt(accident.getCreatedAt())
                .updatedAt(accident.getUpdatedAt())
                .build();
    }
}
