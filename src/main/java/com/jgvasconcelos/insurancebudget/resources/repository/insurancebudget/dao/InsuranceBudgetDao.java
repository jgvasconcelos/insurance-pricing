package com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.dao;

import com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.entity.InsuranceBudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InsuranceBudgetDao extends JpaRepository<InsuranceBudgetEntity, String> {
    List<InsuranceBudgetEntity> findByDriverId(String driverId);
    List<InsuranceBudgetEntity> findByCarId(String carId);

    @Modifying
    @Query("DELETE FROM InsuranceBudget ib WHERE ib.id = :insuranceBudgetId")
    Integer deleteInsuranceBudgetById(@Param("insuranceBudgetId") String insuranceBudgetId);
}
