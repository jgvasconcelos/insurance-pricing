package com.jgvasconcelos.insurancebudget.application.web.dto.response;

import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class DriverResponseDto {
    private String id;
    private String name;
    private String document;
    private LocalDate birthdate;
    private List<AccidentResponseDto> accidents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static DriverResponseDto fromModel(Driver driver) {
        DriverResponseDto driverResponseDto = DriverResponseDto.builder()
                .id(driver.getId())
                .name(driver.getName())
                .document(driver.getDocument())
                .birthdate(driver.getBirthdate())
                .createdAt(driver.getCreatedAt())
                .updatedAt(driver.getUpdatedAt())
                .build();

        if (driver.getAccidents() != null) {
            driverResponseDto.accidents = driver.getAccidents().stream().map(AccidentResponseDto::fromDriverAccidentModel).toList();
        }

        return driverResponseDto;
    }

    public static DriverResponseDto fromModelWithoutNested(Driver driver) {

        return DriverResponseDto.builder()
                .id(driver.getId())
                .name(driver.getName())
                .document(driver.getDocument())
                .birthdate(driver.getBirthdate())
                .createdAt(driver.getCreatedAt())
                .updatedAt(driver.getUpdatedAt())
                .build();
    }
}
