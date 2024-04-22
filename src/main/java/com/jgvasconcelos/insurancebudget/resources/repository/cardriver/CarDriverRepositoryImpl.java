package com.jgvasconcelos.insurancebudget.resources.repository.cardriver;

import com.jgvasconcelos.insurancebudget.domain.model.CarDriver;
import com.jgvasconcelos.insurancebudget.domain.repository.CarDriverRepository;
import com.jgvasconcelos.insurancebudget.resources.repository.cardriver.dao.CarDriverDao;
import com.jgvasconcelos.insurancebudget.resources.repository.cardriver.entity.CarDriverEntity;
import com.jgvasconcelos.insurancebudget.resources.repository.cardriver.exception.CarDriverNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@AllArgsConstructor
public class CarDriverRepositoryImpl implements CarDriverRepository {
    private final CarDriverDao carDriverDao;

    @Override
    public CarDriver add(CarDriver carDriver) {
        CarDriverEntity carDriverEntity = CarDriverEntity.fromModel(carDriver);

        CarDriverEntity savedCarDriver = carDriverDao.save(carDriverEntity);

        return savedCarDriver.toModel();
    }

    @Override
    public CarDriver getById(String carDriverId) throws CarDriverNotFoundException {
        Optional<CarDriverEntity> optionalRetrievedCarDriverEntity = carDriverDao.findById(carDriverId);

        return optionalRetrievedCarDriverEntity.orElseThrow(
                () -> {
                    log.error("Car driver relation with Id: [{}] was not found while trying to retrieve it.", carDriverId);

                    return new CarDriverNotFoundException("Car driver relation with Id: [" + carDriverId + "] was not found while trying to retrieve it.");
                }
        ).toModel();
    }

    @Override
    public List<CarDriver> getAllByDriverId(String driverId) {
        List<CarDriverEntity> retrievedCarDrivers = carDriverDao.findByDriverId(driverId);

        return retrievedCarDrivers.stream().map(CarDriverEntity::toModel).toList();
    }

    @Override
    public List<CarDriver> getAllByCarId(String carId) {
        List<CarDriverEntity> retrievedCarDrivers = carDriverDao.findByCarId(carId);

        return retrievedCarDrivers.stream().map(CarDriverEntity::toModel).toList();
    }

    @Override
    @Transactional
    public void deleteById(String carDriverId) throws CarDriverNotFoundException {
        Integer deletedCarDrivers = carDriverDao.deleteCarDriverById(carDriverId);

        if (deletedCarDrivers == 0) {
            log.error("Car driver relation with Id: [{}] was not found while trying to delete it.", carDriverId);

            throw new CarDriverNotFoundException("Car driver relation with Id: [" + carDriverId + "] was not found while trying to delete it.");
        }
    }
}
