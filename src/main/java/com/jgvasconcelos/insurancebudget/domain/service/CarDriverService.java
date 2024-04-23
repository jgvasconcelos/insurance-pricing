package com.jgvasconcelos.insurancebudget.domain.service;

import com.jgvasconcelos.insurancebudget.domain.model.CarDriver;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.cardriver.exception.CarDriverNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;

import java.util.List;

public interface CarDriverService {
    CarDriver create(CarDriver carDriver) throws DriverNotFoundException, CarNotFoundException;
    CarDriver getById(String carDriverId) throws CarDriverNotFoundException;
    List<CarDriver> getAllByDriverId(String driverId);
    List<CarDriver> getAllByCarId(String carId);
    void deleteById(String carDriverId) throws CarDriverNotFoundException;
}
