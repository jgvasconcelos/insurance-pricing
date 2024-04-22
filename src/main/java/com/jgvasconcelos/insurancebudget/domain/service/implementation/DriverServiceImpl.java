package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.domain.repository.DriverRepository;
import com.jgvasconcelos.insurancebudget.domain.service.DriverService;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    @Override
    public Driver createDriver(Driver driver) {
        log.info("Creating a new driver with Name: [{}].", driver.getName());

        Driver createdDriver = driverRepository.add(driver);

        log.info("Successfully created a new driver with Id: [{}] and Name: [{}].", driver.getId(), driver.getName());

        return createdDriver;
    }

    @Override
    public Driver getById(String driverId) throws DriverNotFoundException {
        log.info("Retrieving driver with Id: [{}].", driverId);

        Driver retrievedDriver = driverRepository.getById(driverId);

        log.info("Successfully retrieved driver with Id: [{}], Document [{}] and Name [{}].", retrievedDriver.getId(), retrievedDriver.getDocument(), retrievedDriver.getName());

        return retrievedDriver;
    }

    @Override
    public Driver getByDocument(String document) throws DriverNotFoundException {
        log.info("Retrieving driver with Document: [{}].", document);

        Driver retrievedDriver = driverRepository.getByDocument(document);

        log.info("Successfully retrieved driver with Id: [{}], Document [{}] and Name [{}].", retrievedDriver.getId(), retrievedDriver.getDocument(), retrievedDriver.getName());

        return retrievedDriver;
    }

    @Override
    public Driver updateDriver(Driver driver) {
        return null;
    }

    @Override
    public void deleteById(String driverId) {

    }

    @Override
    public void deleteByDocument(String driverDocument) {

    }
}
