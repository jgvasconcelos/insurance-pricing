package com.jgvasconcelos.insurancebudget.domain.repository;

import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;

import java.math.BigDecimal;

public interface CarRepository {
    Car add(Car car);
    Car getById(String carId) throws CarNotFoundException;
    Car updateFipeValueById(String carId, BigDecimal newFipeValue) throws CarNotFoundException;
    void deleteById(String carId) throws CarNotFoundException;
}
