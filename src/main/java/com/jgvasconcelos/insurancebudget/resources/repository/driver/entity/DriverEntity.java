package com.jgvasconcelos.insurancebudget.resources.repository.driver.entity;

import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Driver")
@Table(name = "drivers")
public class DriverEntity {
    @Id
    @UuidGenerator
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "document", nullable = false)
    private String document;
    @Column(name = "birthdate", unique = true, nullable = false)
    private LocalDate birthdate;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Driver toModel() {
        return Driver.builder()
                .id(this.id)
                .name(this.name)
                .document(this.document)
                .birthdate(this.birthdate)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public static DriverEntity fromModel(Driver driver) {
        return DriverEntity.builder()
                .id(driver.getId())
                .name(driver.getName())
                .document(driver.getDocument())
                .birthdate(driver.getBirthdate())
                .createdAt(driver.getCreatedAt())
                .updatedAt(driver.getUpdatedAt())
                .build();
    }

    public void updateChangedValues(DriverEntity other) {
        if (other.getName() != null && !this.name.equals(other.getName())) this.name = other.getName();
        if (other.getDocument() != null &&!this.document.equals(other.getDocument())) this.document = other.getDocument();
        if (other.getBirthdate() != null && !this.birthdate.equals(other.getBirthdate())) this.birthdate = other.birthdate;
    }
}
