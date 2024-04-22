package com.jgvasconcelos.insurancebudget.domain.service;

import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverAlreadyExistsException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;

public interface DriverService {
    Driver createDriver(Driver driver) throws DriverAlreadyExistsException;
    Driver getById(String driverId) throws DriverNotFoundException;
    Driver getByDocument(String document) throws DriverNotFoundException;
    Driver updateDriver(Driver driver) throws DriverNotFoundException;
    void deleteById(String driverId) throws DriverNotFoundException;
    void deleteByDocument(String driverDocument) throws DriverNotFoundException;
}
