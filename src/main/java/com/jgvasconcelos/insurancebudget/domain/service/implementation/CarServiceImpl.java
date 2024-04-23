package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.domain.repository.CarRepository;
import com.jgvasconcelos.insurancebudget.domain.service.CarService;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car create(Car car) {
        log.info("Creating a new car with Model: [{}] and Manufacturer: [{}].", car.getModel(), car.getManufacturer());

        Car createdCar = carRepository.add(car);

        log.info("Successfully created a new car with Id: [{}], Model: [{}] and Manufacturer: [{}].", createdCar.getId(), createdCar.getModel(), createdCar.getManufacturer());

        return createdCar;
    }

    @Override
    public Car getById(String carId) throws CarNotFoundException {
        log.info("Retrieving car with Id: [{}].", carId);

        Car retrievedCar = carRepository.getById(carId);

        log.info("Successfully retrieved car with Id: [{}], Model: [{}] and Manufacturer: [{}].", retrievedCar.getId(), retrievedCar.getModel(), retrievedCar.getManufacturer());

        return retrievedCar;
    }

    @Override
    @Transactional
    public Car updateFipeValueById(String carId, BigDecimal newFipeValue) throws CarNotFoundException {
        log.info("Updating Fipe value of car with Id: [{}] to new Fipe value: [{}].", carId, newFipeValue);

        Car updatedCar = carRepository.updateFipeValueById(carId, newFipeValue);

        log.info("Successfully updated Fipe value of car with Id: [{}] to new Fipe value: [{}].", updatedCar.getId(), updatedCar.getFipeValue());

        return updatedCar;
    }

    @Override
    @Transactional
    public void deleteById(String carId) throws CarNotFoundException {
        log.info("Deleting car with Id: [{}].", carId);

        carRepository.deleteById(carId);

        log.info("Successfully deleted car with Id: [{}].", carId);
    }
}
