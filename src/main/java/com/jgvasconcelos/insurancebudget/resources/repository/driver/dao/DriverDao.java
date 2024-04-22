package com.jgvasconcelos.insurancebudget.resources.repository.driver.dao;

import com.jgvasconcelos.insurancebudget.resources.repository.driver.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DriverDao extends JpaRepository<DriverEntity, String> {
    Optional<DriverEntity> findByDocument(String document);

    @Modifying
    @Query(value = "DELETE FROM Driver d WHERE d.id = :driverId")
    Integer deleteDriverById(@Param("driverId") String driverId);

    @Modifying
    @Query(value = "DELETE FROM Driver d WHERE d.driverDocument = :driverDocument")
    Integer deleteDriverByDocument(@Param("driverDocument") String driverDocument);
}
