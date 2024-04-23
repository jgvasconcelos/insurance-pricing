package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.exception.DriverIsNotAssociatedWithCarException;
import com.jgvasconcelos.insurancebudget.domain.exception.DriverIsNotMainDriverOfCarException;
import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.domain.model.CarDriver;
import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.domain.model.InsuranceBudget;
import com.jgvasconcelos.insurancebudget.domain.repository.InsuranceBudgetRepository;
import com.jgvasconcelos.insurancebudget.domain.service.CarDriverService;
import com.jgvasconcelos.insurancebudget.domain.service.CarService;
import com.jgvasconcelos.insurancebudget.domain.service.DriverService;
import com.jgvasconcelos.insurancebudget.domain.service.InsuranceBudgetService;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.exception.InsuranceBudgetNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class InsuranceBudgetServiceImpl implements InsuranceBudgetService {
    private final DriverService driverService;
    private final CarService carService;
    private final CarDriverService carDriverService;
    private final InsuranceBudgetRepository insuranceBudgetRepository;

    @Override
    public InsuranceBudget createInsuranceBudget(InsuranceBudget insuranceBudget) throws DriverNotFoundException, CarNotFoundException, DriverIsNotAssociatedWithCarException, DriverIsNotMainDriverOfCarException {
        log.info("Creating a new Insurance Budget for DriverId: [{}] and Car Id: [{}].", insuranceBudget.getDriver().getId(), insuranceBudget.getCar().getId());

        Driver insuranceDriver = driverService.getById(insuranceBudget.getDriver().getId());
        Car insuranceCar = carService.getById(insuranceBudget.getCar().getId());

        checkIfDriverIsRelatedToCar(insuranceDriver, insuranceCar);

        InsuranceBudget insuranceBudgetToCreate = InsuranceBudget.builder()
                .car(insuranceCar)
                .driver(insuranceDriver)
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        insuranceBudgetToCreate.setBudgetAmount(insuranceBudgetToCreate.calculateInsuranceBudgetAmount());

        InsuranceBudget createdInsuranceBudget = insuranceBudgetRepository.add(insuranceBudgetToCreate);

        log.info("Successfully created a new Insurance Budget for DriverId: [{}] and Car Id: [{}].", createdInsuranceBudget.getDriver().getId(), createdInsuranceBudget.getCar().getId());

        return createdInsuranceBudget;
    }

    private void checkIfDriverIsRelatedToCar(Driver insuranceDriver, Car insuranceCar) throws DriverIsNotAssociatedWithCarException, DriverIsNotMainDriverOfCarException {
        List<CarDriver> insuranceDriverCars = carDriverService.getAllByDriverId(insuranceDriver.getId());

        List<CarDriver> carsDrivenByInsuranceDriver = insuranceDriverCars.stream().filter( carDriver -> carDriver.getDriver().getId().equals(insuranceDriver.getId()) ).toList();

        if (carsDrivenByInsuranceDriver.isEmpty()) {
            log.error("Driver with Id: [{}] is not associated to Car with Id: [{}].", insuranceDriver.getId(), insuranceCar.getId());

            throw new DriverIsNotAssociatedWithCarException("Driver with Id: [" + insuranceDriver.getId() + "] is not associated to Car with Id: [" + insuranceCar.getId() + "].");
        }

        List<CarDriver> carsThatHaveInsuranceDriverAsMainDriver = carsDrivenByInsuranceDriver.stream().filter(CarDriver::getIsMainDriver).toList();

        if (carsThatHaveInsuranceDriverAsMainDriver.isEmpty()) {
            log.error("Driver with Id: [{}] is not main driver of Car with Id: [{}].", insuranceDriver.getId(), insuranceCar.getId());

            throw new DriverIsNotMainDriverOfCarException("Driver with Id: [" + insuranceDriver.getId() + "] is not main driver of Car with Id: [" + insuranceCar.getId() + "].");
        }
    }

    @Override
    public InsuranceBudget getById(String insuranceBudgetId) throws InsuranceBudgetNotFoundException {
        log.info("Retrieving insurance budget with Id: [{}].", insuranceBudgetId);

        InsuranceBudget retrievedInsuranceBudget = insuranceBudgetRepository.getById(insuranceBudgetId);

        log.info("Successfully retrieved insurance budget with Id: [{}].", retrievedInsuranceBudget.getId());

        return retrievedInsuranceBudget;
    }

    @Override
    public List<InsuranceBudget> getByDriverId(String driverId) {
        log.info("Retrieving all insurance budgets for driver with Id: [{}].", driverId);

        List<InsuranceBudget> retrievedInsuranceBudgets = insuranceBudgetRepository.getByDriverId(driverId);

        log.info("Successfully retrieved all drivers for driver with Id: [{}].", driverId);

        return retrievedInsuranceBudgets;
    }

    @Override
    public List<InsuranceBudget> getByCarId(String carId) {
        log.info("Retrieving all insurance budgets for car with Id: [{}].", carId);

        List<InsuranceBudget> retrievedInsuranceBudgets = insuranceBudgetRepository.getByCarId(carId);

        log.info("Successfully retrieved all drivers for car with Id: [{}].", carId);

        return retrievedInsuranceBudgets;
    }

    @Override
    @Transactional
    public InsuranceBudget updateInsuranceBudget(InsuranceBudget insuranceBudget) throws InsuranceBudgetNotFoundException, DriverNotFoundException, CarNotFoundException {
        log.info("Updating insurance budget with Id: [{}].", insuranceBudget.getId());

        Driver insuranceDriver = driverService.getById(insuranceBudget.getDriver().getId());
        Car insuranceCar = carService.getById(insuranceBudget.getCar().getId());

        insuranceBudget.setDriver(insuranceDriver);
        insuranceBudget.setCar(insuranceCar);

        InsuranceBudget alreadyExistingInsuranceBudget = insuranceBudgetRepository.getById(insuranceBudget.getId());
        alreadyExistingInsuranceBudget.updateChangedValues(insuranceBudget);
        alreadyExistingInsuranceBudget.setUpdatedAt(LocalDateTime.now());

        InsuranceBudget updatedInsuranceBudget = insuranceBudgetRepository.updateInsuranceBudget(insuranceBudget);

        log.info("Successfully updated insurance budget with Id: [{}].", insuranceBudget.getId());

        return updatedInsuranceBudget;
    }

    @Override
    @Transactional
    public void deleteById(String insuranceBudgetId) throws InsuranceBudgetNotFoundException {
        log.info("Deleting insurance budget with Id: [{}].", insuranceBudgetId);

        insuranceBudgetRepository.deleteById(insuranceBudgetId);

        log.info("Successfully deleted insurance budget with Id: [{}].", insuranceBudgetId);
    }
}
