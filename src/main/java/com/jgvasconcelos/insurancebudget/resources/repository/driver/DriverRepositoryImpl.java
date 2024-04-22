package com.jgvasconcelos.insurancebudget.resources.repository.driver;

import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.domain.repository.DriverRepository;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.dao.DriverDao;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.entity.DriverEntity;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@AllArgsConstructor
public class DriverRepositoryImpl implements DriverRepository {
    private final DriverDao driverDao;

    @Override
    public Driver add(Driver driver) {
        DriverEntity driverEntity = DriverEntity.fromModel(driver);

        DriverEntity savedDriver = driverDao.save(driverEntity);

        return savedDriver.toModel();
    }

    @Override
    public Driver getById(String driverId) throws DriverNotFoundException {
        Optional<DriverEntity> optionalRetrievedDriverEntity = driverDao.findById(driverId);

        return optionalRetrievedDriverEntity.orElseThrow(
                () -> {
                    log.error("Driver with Id: {} was not found while trying to retrieve it.", driverId);

                    return new DriverNotFoundException("Driver with Id [" + driverId + "] was not found while trying to retrieve it.");
                }
        ).toModel();
    }

    @Override
    public Driver getByDocument(String driverDocument) throws DriverNotFoundException {
        Optional<DriverEntity> optionalRetrievedDriverEntity = driverDao.findByDocument(driverDocument);

        return optionalRetrievedDriverEntity.orElseThrow(
                () -> {
                    log.error("Driver with Document: {} was not found while trying to retrieve it.", driverDocument);

                    return new DriverNotFoundException("Driver with Document [" + driverDocument + "] was not found while trying to retrieve it.");
                }
        ).toModel();
    }

    @Override
    public Driver updateDriver(Driver driver) throws DriverNotFoundException {
        DriverEntity driverEntity = DriverEntity.fromModel(driver);

        Driver alreadyExistingDriver = getById(driver.getId());
        DriverEntity alreadyExistingDriverEntity = DriverEntity.fromModel(alreadyExistingDriver);

        alreadyExistingDriverEntity.updateChangedValues(driverEntity);

        DriverEntity updatedDriverEntity = driverDao.save(alreadyExistingDriverEntity);

        return updatedDriverEntity.toModel();
    }

    @Override
    public void deleteById(String driverId) throws DriverNotFoundException {
        Integer deletedDrivers = driverDao.deleteDriverById(driverId);

        if (deletedDrivers == 0) {
            log.error("Driver with Id: {} was not found while trying to delete it.", driverId);

            throw new DriverNotFoundException("Driver with Id [" + driverId + "] was not found while trying to retrieve it.");
        }
    }

    @Override
    public void deleteByDocument(String driverDocument) throws DriverNotFoundException {
        Integer deletedDrivers = driverDao.deleteDriverByDocument(driverDocument);

        if (deletedDrivers == 0) {
            log.error("Driver with Document: {} was not found while trying to delete it.", driverDocument);

            throw new DriverNotFoundException("Driver with Document [" + driverDocument + "] was not found while trying to retrieve it.");
        }
    }
}
