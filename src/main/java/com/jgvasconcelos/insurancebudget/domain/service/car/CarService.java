package com.jgvasconcelos.insurancebudget.domain.service.car;

import com.jgvasconcelos.insurancebudget.domain.model.car.Car;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;

public interface CarService {
    Car add(Car car);
    Car getById(String carId) throws CarNotFoundException;
    Car updateFipeValueById(String carId, Float newFipeValue) throws CarNotFoundException;
    void deleteById(String carId) throws CarNotFoundException;
}
