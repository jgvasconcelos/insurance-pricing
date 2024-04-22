package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.domain.model.CarDriver;
import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.domain.repository.CarDriverRepository;
import com.jgvasconcelos.insurancebudget.domain.service.CarDriverService;
import com.jgvasconcelos.insurancebudget.domain.service.CarService;
import com.jgvasconcelos.insurancebudget.domain.service.DriverService;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class CarDriverServiceImpl implements CarDriverService {
    private final DriverService driverService;
    private final CarService carService;
    private final CarDriverRepository carDriverRepository;

    @Override
    public CarDriver create(String driverId, String carId, Boolean isMainDriver) throws DriverNotFoundException, CarNotFoundException {
        log.info("Creating new car driver relation for Driver: [{}], Car [{}] and Is Main Driver: [{}].", driverId, carId, isMainDriver);

        Driver driver = driverService.getById(driverId);
        Car car = carService.getById(carId);

        CarDriver carDriverToCreate = CarDriver.builder()
                .driver(driver)
                .car(car)
                .isMainDriver(isMainDriver)
                .build();

        CarDriver createdCarDriver = carDriverRepository.add(carDriverToCreate);

        log.info("Successfully created new car driver relation for Driver: [{}], Car [{}] and Is Main Driver: [{}].", createdCarDriver.getDriver().getId(), createdCarDriver.getCar().getId(), createdCarDriver.getIsMainDriver());

        return createdCarDriver;
    }

    @Override
    public CarDriver getById(String carDriverId) {
        log.info("Retrieving car driver relation with Id: [{}].", carDriverId);

        CarDriver retrievedCarDriver = carDriverRepository.getById(carDriverId);

        log.info("Successfully retrieved car driver relation with Id: [{}].", carDriverId);

        return retrievedCarDriver;
    }

    @Override
    public List<CarDriver> getAllByDriverId(String driverId) {
        log.info("Retrieving all cars for driver with Id: [{}].", driverId);

        List<CarDriver> retrievedCarDrivers = carDriverRepository.getAllByDriverId(driverId);

        log.info("Successfully retrieved all cars for driver with Id: [{}].", driverId);

        return retrievedCarDrivers;
    }

    @Override
    public List<CarDriver> getAllByCarId(String carId) {
        log.info("Retrieving all drivers for car with Id: [{}].", carId);

        List<CarDriver> retrievedCarDrivers = carDriverRepository.getAllByCarId(carId);

        log.info("Successfully retrieved all drivers for car with Id: [{}].", carId);

        return retrievedCarDrivers;
    }

    @Override
    @Transactional
    public void deleteById(String carDriverId) {
        log.info("Deleting car driver relation with Id: [{}].", carDriverId);

        carDriverRepository.deleteById(carDriverId);

        log.info("Successfully deleted car driver relation with Id: [{}].", carDriverId);
    }
}
