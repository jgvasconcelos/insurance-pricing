package com.jgvasconcelos.insurancebudget.application.web.dto.request;

import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDriverRequestDto {
    @NotBlank(message = "Name should not be null, empty or blank.")
    private String name;
    @NotBlank(message = "Document should not be null, empty or blank.")
    private String document;
    @NotNull(message = "Birthdate should not be null.")
    private LocalDate birthdate;

    public Driver toModel() {
        return Driver.builder()
                .name(this.name)
                .document(this.document)
                .birthdate(this.birthdate)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
