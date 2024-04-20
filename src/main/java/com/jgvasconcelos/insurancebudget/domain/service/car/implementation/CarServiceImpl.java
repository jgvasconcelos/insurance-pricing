package com.jgvasconcelos.insurancebudget.domain.service.car.implementation;

import com.jgvasconcelos.insurancebudget.domain.model.car.Car;
import com.jgvasconcelos.insurancebudget.domain.repository.CarRepository;
import com.jgvasconcelos.insurancebudget.domain.service.car.CarService;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    @Override
    public Car add(Car car) {
        return carRepository.add(car);
    }

    @Override
    public Car getById(String carId) throws CarNotFoundException {
        return carRepository.getById(carId);
    }

    @Override
    @Transactional
    public Car updateFipeValueById(String carId, Float newFipeValue) throws CarNotFoundException {
        return carRepository.updateFipeValueById(carId, newFipeValue);
    }

    @Override
    @Transactional
    public void deleteById(String carId) throws CarNotFoundException {
        carRepository.deleteById(carId);
    }
}
