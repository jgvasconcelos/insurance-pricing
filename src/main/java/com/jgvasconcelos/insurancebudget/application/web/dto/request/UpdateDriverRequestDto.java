package com.jgvasconcelos.insurancebudget.application.web.dto.request;

import com.jgvasconcelos.insurancebudget.application.web.validator.NullOrNotBlank;
import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDriverRequestDto {
    @NullOrNotBlank(message = "Name should not be empty or blank.")
    private String name;
    @NullOrNotBlank(message = "Document should not be empty or blank.")
    private String document;
    private LocalDate birthdate;

    public Driver toModel() {
        return Driver.builder()
                .name(this.name)
                .document(this.document)
                .birthdate(this.birthdate)
                .build();
    }
}
