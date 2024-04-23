package com.jgvasconcelos.insurancebudget.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

@Data
@Builder
public class Driver {
    private String id;
    private String name;
    private String document;
    private LocalDate birthdate;
    private List<Accident> accidents;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Integer calculateAgeInYears() {
        return Period.between(this.birthdate, LocalDate.now()).getYears();
    }

    public void updateChangedValues(Driver other) {
        if (other.getName() != null && !this.name.equals(other.getName())) this.name = other.getName();
        if (other.getDocument() != null &&!this.document.equals(other.getDocument())) this.document = other.getDocument();
        if (other.getBirthdate() != null && !this.birthdate.equals(other.getBirthdate())) this.birthdate = other.birthdate;
    }
}
