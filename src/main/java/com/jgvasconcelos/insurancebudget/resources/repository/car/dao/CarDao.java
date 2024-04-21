package com.jgvasconcelos.insurancebudget.resources.repository.car.dao;

import com.jgvasconcelos.insurancebudget.resources.repository.car.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CarDao extends JpaRepository<CarEntity, String> {
    @Modifying
    @Query(value = "DELETE FROM Car c WHERE c.id = :carId")
    Integer deleteCarById(@Param("carId") String carId);

    @Modifying
    @Query(value = "UPDATE Car c SET c.fipeValue = :newFipeValue, c.updatedAt = CURRENT_TIMESTAMP WHERE c.id = :carId")
    Integer updateFipeValueById(@Param("carId") String carId, @Param("newFipeValue") Float newFipeValue);
}
