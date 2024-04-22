package com.jgvasconcelos.insurancebudget.resources.repository.accident.dao;

import com.jgvasconcelos.insurancebudget.resources.repository.accident.entity.AccidentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccidentDao extends JpaRepository<AccidentEntity, String> {
    List<AccidentEntity> findByCarId(String carId);
    List<AccidentEntity> findByDriverId(String driverId);

    @Modifying
    @Query(value = "DELETE FROM Accident a WHERE a.id = :accidentId")
    Integer deleteAccidentById(@Param("accidentId") String accidentId);
}
