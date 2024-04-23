package com.jgvasconcelos.insurancebudget.domain.repository;

import com.jgvasconcelos.insurancebudget.domain.model.InsuranceBudget;
import com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.exception.InsuranceBudgetNotFoundException;

import java.util.List;

public interface InsuranceBudgetRepository {
    InsuranceBudget add(InsuranceBudget insuranceBudget);
    InsuranceBudget getById(String insuranceBudgetId) throws InsuranceBudgetNotFoundException;
    List<InsuranceBudget> getByDriverId(String driverId);
    List<InsuranceBudget> getByCarId(String carId);
    InsuranceBudget updateInsuranceBudget(InsuranceBudget insuranceBudget) throws InsuranceBudgetNotFoundException;
    void deleteById(String insuranceBudgetId) throws InsuranceBudgetNotFoundException;
}
