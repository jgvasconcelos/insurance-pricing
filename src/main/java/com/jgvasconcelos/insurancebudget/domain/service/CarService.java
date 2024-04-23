package com.jgvasconcelos.insurancebudget.domain.service;

import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;

import java.math.BigDecimal;

public interface CarService {
    Car create(Car car);
    Car getById(String carId) throws CarNotFoundException;
    Car updateFipeValueById(String carId, BigDecimal newFipeValue) throws CarNotFoundException;
    void deleteById(String carId) throws CarNotFoundException;
}
