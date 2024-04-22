package com.jgvasconcelos.insurancebudget.domain.service;

import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;

public interface CarService {
    Car create(Car car);
    Car getById(String carId) throws CarNotFoundException;
    Car updateFipeValueById(String carId, Float newFipeValue) throws CarNotFoundException;
    void deleteById(String carId) throws CarNotFoundException;
}
