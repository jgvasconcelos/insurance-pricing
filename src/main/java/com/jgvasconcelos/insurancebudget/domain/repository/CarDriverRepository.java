package com.jgvasconcelos.insurancebudget.domain.repository;

import com.jgvasconcelos.insurancebudget.domain.model.CarDriver;

import java.util.List;

public interface CarDriverRepository {
    CarDriver add(CarDriver carDriver);
    CarDriver getById(String carDriverId);
    List<CarDriver> getAllByDriverId(String driverId);
    List<CarDriver> getAllByCarId(String carId);
    void deleteById(String carDriverId);
}
