package com.jgvasconcelos.insurancebudget.resources.repository;

import com.jgvasconcelos.insurancebudget.domain.model.car.Car;
import com.jgvasconcelos.insurancebudget.domain.repository.CarRepository;
import com.jgvasconcelos.insurancebudget.resources.repository.car.dao.CarDao;
import com.jgvasconcelos.insurancebudget.resources.repository.car.entity.CarEntity;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class CarRepositoryImpl implements CarRepository {
    private final CarDao carDao;

    @Override
    public Car add(Car car) {
        CarEntity carEntity = CarEntity.fromModel(car);

        CarEntity savedCar = carDao.save(carEntity);

        return savedCar.toModel();
    }

    @Override
    public Car getById(String carId) throws CarNotFoundException {
        Optional<CarEntity> optionalRetrievedCarEntity = carDao.findById(carId);

        return optionalRetrievedCarEntity.orElseThrow(
                () -> new CarNotFoundException("Car with id [" + carId + "] was not found.")
        ).toModel();
    }

    @Override
    public Car updateFipeValueById(String carId, Float newFipeValue) throws CarNotFoundException {
        Integer updatedCars = carDao.updateFipeValueById(carId, newFipeValue);

        if (updatedCars == 0) throw new CarNotFoundException("Car with id [" + carId + "] was not found.");

        return getById(carId);
    }

    @Override
    public void deleteById(String carId) throws CarNotFoundException {
        Integer deletedCars = carDao.deleteCarById(carId);

        if (deletedCars == 0) throw new CarNotFoundException("Car with id [" + carId + "] was not found.");
    }
}
