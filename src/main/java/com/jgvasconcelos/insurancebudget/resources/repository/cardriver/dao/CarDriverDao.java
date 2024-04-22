package com.jgvasconcelos.insurancebudget.resources.repository.cardriver.dao;

import com.jgvasconcelos.insurancebudget.resources.repository.cardriver.entity.CarDriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarDriverDao extends JpaRepository<CarDriverEntity, String> {
    List<CarDriverEntity> findByDriverId(String driverId);
    List<CarDriverEntity> findByCarId(String carId);

    @Modifying
    @Query(value = "DELETE FROM CarDriver cd WHERE cd.id = :carDriverId")
    Integer deleteCarDriverById(@Param("carDriverId") String carDriverId);
}
