package com.jgvasconcelos.insurancebudget.domain.repository;

import com.jgvasconcelos.insurancebudget.domain.model.CarDriver;
import com.jgvasconcelos.insurancebudget.resources.repository.cardriver.exception.CarDriverNotFoundException;

import java.util.List;

public interface CarDriverRepository {
    CarDriver add(CarDriver carDriver);
    CarDriver getById(String carDriverId) throws CarDriverNotFoundException;
    List<CarDriver> getAllByDriverId(String driverId);
    List<CarDriver> getAllByCarId(String carId);
    void deleteById(String carDriverId) throws CarDriverNotFoundException;
}
