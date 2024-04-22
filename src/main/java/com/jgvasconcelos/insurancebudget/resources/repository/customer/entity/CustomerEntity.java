package com.jgvasconcelos.insurancebudget.resources.repository.customer.entity;

import com.jgvasconcelos.insurancebudget.resources.repository.driver.entity.DriverEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
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
@Entity(name = "Customer")
@Table(name = "customers")
public class CustomerEntity {
    @Id
    @UuidGenerator
    @Column(name = "id")
    private String id;
    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany
    private List<DriverEntity> drivers;
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
