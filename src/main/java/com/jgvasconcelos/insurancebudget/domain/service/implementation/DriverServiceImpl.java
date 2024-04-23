package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.exception.DriverIsAgeMinor;
import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.domain.repository.DriverRepository;
import com.jgvasconcelos.insurancebudget.domain.service.DriverService;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverAlreadyExistsException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    private static final Integer DRIVER_MINIMUM_AGE = 18;

    @Override
    public Driver createDriver(Driver driver) throws DriverAlreadyExistsException, DriverIsAgeMinor {
        log.info("Creating a new driver with Name: [{}].", driver.getName());

        Integer driverAge = driver.calculateAgeInYears();

        if (driverAge < DRIVER_MINIMUM_AGE) {
            throw new DriverIsAgeMinor("Driver with Name: [" + driver.getName() + "] is age minor.");
        }

        Driver createdDriver = driverRepository.add(driver);

        log.info("Successfully created a new driver with Id: [{}] and Name: [{}].", createdDriver.getId(), createdDriver.getName());

        return createdDriver;
    }

    @Override
    public Driver getById(String driverId) throws DriverNotFoundException {
        log.info("Retrieving driver with Id: [{}].", driverId);

        Driver retrievedDriver = driverRepository.getById(driverId);

        log.info("Successfully retrieved driver with Id: [{}] and Name [{}].", retrievedDriver.getId(), retrievedDriver.getName());

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
    @Transactional
    public Driver updateDriver(Driver driver) throws DriverNotFoundException {
        log.info("Updating driver with Id: [{}].", driver.getId());

        Driver alreadyExistingDriver = driverRepository.getById(driver.getId());
        alreadyExistingDriver.updateChangedValues(driver);
        alreadyExistingDriver.setUpdatedAt(LocalDateTime.now());

        Driver updatedDriver = driverRepository.updateDriver(alreadyExistingDriver);

        log.info("Successfully updated driver with Id: [{}], Document [{}] and Name [{}].", updatedDriver.getId(), updatedDriver.getDocument(), updatedDriver.getName());

        return updatedDriver;
    }

    @Override
    @Transactional
    public void deleteById(String driverId) throws DriverNotFoundException {
        log.info("Deleting driver with Id: [{}].", driverId);

        driverRepository.deleteById(driverId);

        log.info("Successfully deleted driver with Id: [{}].", driverId);
    }

    @Override
    @Transactional
    public void deleteByDocument(String driverDocument) throws DriverNotFoundException {
        log.info("Deleting driver with Document: [{}].", driverDocument);

        driverRepository.deleteByDocument(driverDocument);

        log.info("Successfully deleted driver with Document: [{}].", driverDocument);
    }
}
