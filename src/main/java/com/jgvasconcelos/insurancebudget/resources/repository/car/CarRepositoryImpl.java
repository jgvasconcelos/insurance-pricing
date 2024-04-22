package com.jgvasconcelos.insurancebudget.resources.repository.car;

import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.domain.repository.CarRepository;
import com.jgvasconcelos.insurancebudget.resources.repository.car.dao.CarDao;
import com.jgvasconcelos.insurancebudget.resources.repository.car.entity.CarEntity;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
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
                () -> {
                    log.error("Car with Id: [{}] was not found while trying to retrieve it.", carId);

                    return new CarNotFoundException("Car with Id [" + carId + "] was not found while trying to retrieve it.");
                }
        ).toModel();
    }

    @Override
    public Car updateFipeValueById(String carId, Float newFipeValue) throws CarNotFoundException {
        Integer updatedCars = carDao.updateFipeValueById(carId, newFipeValue);

        if (updatedCars == 0) {
            log.error("Car with Id: [{}] was not found while trying to update Fipe value.", carId);

            throw new CarNotFoundException("Car with Id [" + carId + "] was not found while trying to update Fipe value.");
        }

        return getById(carId);
    }

    @Override
    public void deleteById(String carId) throws CarNotFoundException {
        Integer deletedCars = carDao.deleteCarById(carId);

        if (deletedCars == 0) {
            log.error("Car with Id: [{}] was not found while trying to delete it.", carId);

            throw new CarNotFoundException("Car with Id [" + carId + "] was not found while trying to delete it.");
        }
    }
}
