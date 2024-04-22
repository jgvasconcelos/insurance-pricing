package com.jgvasconcelos.insurancebudget.domain.repository;

import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;

public interface DriverRepository {
    Driver add(Driver driver);
    Driver getById(String driverId) throws DriverNotFoundException;
    Driver getByDocument(String driverDocument) throws DriverNotFoundException;
    Driver updateDriver(Driver driver) throws DriverNotFoundException;
    void deleteById(String driverId) throws DriverNotFoundException;
    void deleteByDocument(String driverDocument) throws DriverNotFoundException;
}
