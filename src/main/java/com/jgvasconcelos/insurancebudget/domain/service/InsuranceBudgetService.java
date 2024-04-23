package com.jgvasconcelos.insurancebudget.domain.service;

import com.jgvasconcelos.insurancebudget.domain.exception.DriverIsNotAssociatedWithCarException;
import com.jgvasconcelos.insurancebudget.domain.exception.DriverIsNotMainDriverOfCarException;
import com.jgvasconcelos.insurancebudget.domain.model.InsuranceBudget;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.exception.InsuranceBudgetNotFoundException;

import java.util.List;

public interface InsuranceBudgetService {
    InsuranceBudget createInsuranceBudget(InsuranceBudget insuranceBudget) throws DriverNotFoundException, CarNotFoundException, DriverIsNotAssociatedWithCarException, DriverIsNotMainDriverOfCarException;
    InsuranceBudget getById(String insuranceBudgetId) throws InsuranceBudgetNotFoundException;
    List<InsuranceBudget> getByDriverId(String customerId);
    List<InsuranceBudget> getByCarId(String carId);
    InsuranceBudget updateInsuranceBudget(InsuranceBudget insuranceBudget) throws InsuranceBudgetNotFoundException, DriverNotFoundException, CarNotFoundException;
    void deleteById(String insuranceBudgetId) throws InsuranceBudgetNotFoundException;
}
