package com.jgvasconcelos.insurancebudget.domain.service;

import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;

public interface DriverService {
    Driver createDriver(Driver driver);
    Driver getById(String driverId) throws DriverNotFoundException;
    Driver getByDocument(String document) throws DriverNotFoundException;
    Driver updateDriver(Driver driver);
    void deleteById(String driverId);
    void deleteByDocument(String driverDocument);
}
