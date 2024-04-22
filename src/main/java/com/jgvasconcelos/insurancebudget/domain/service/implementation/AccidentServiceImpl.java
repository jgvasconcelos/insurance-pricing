package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.model.Accident;
import com.jgvasconcelos.insurancebudget.domain.model.Car;
import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.domain.repository.AccidentRepository;
import com.jgvasconcelos.insurancebudget.domain.service.AccidentService;
import com.jgvasconcelos.insurancebudget.domain.service.CarService;
import com.jgvasconcelos.insurancebudget.domain.service.DriverService;
import com.jgvasconcelos.insurancebudget.resources.repository.accident.exception.AccidentNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AccidentServiceImpl implements AccidentService {
    private final CarService carService;
    private final DriverService driverService;
    private final AccidentRepository accidentRepository;

    @Override
    public Accident create(String driverId, String carId, LocalDate accidentDate) throws DriverNotFoundException, CarNotFoundException {
        log.info("Creating a new accident with Driver: [{}], Car: [{}] and Date: [{}].", driverId, carId, accidentDate);

        Driver accidentDriver = driverService.getById(driverId);
        Car accidentCar = carService.getById(carId);

        Accident accidentToCreate = Accident.builder()
                .car(accidentCar)
                .driver(accidentDriver)
                .accidentDate(accidentDate)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Accident createdAccident = accidentRepository.add(accidentToCreate);

        log.info("Successfully created a new accident with Id: [{}], Driver: [{}], Car: [{}] and Date: [{}].", createdAccident.getId(), createdAccident.getDriver().getId(), createdAccident.getCar().getId(), createdAccident.getAccidentDate());

        return createdAccident;
    }

    @Override
    public Accident getById(String accidentId) throws AccidentNotFoundException {
        log.info("Retrieving accident with Id: [{}].", accidentId);

        Accident retrievedAccident = accidentRepository.getById(accidentId);

        log.info("Successfully retrievec accident with Id: [{}].", accidentId);

        return retrievedAccident;
    }

    @Override
    public List<Accident> getAllByDriverId(String driverId) {
        log.info("Retrieving all accidents of driver with Id: [{}].", driverId);

        List<Accident> retrievedAccidents = accidentRepository.getAllByDriverId(driverId);

        log.info("Successfully retrieved all accidents of driver with Id: [{}].", driverId);

        return retrievedAccidents;
    }

    @Override
    public List<Accident> getAllByCarId(String cardId) {
        log.info("Retrieving all accidents of car with Id: [{}].", cardId);

        List<Accident> retrievedAccidents = accidentRepository.getAllByCarId(cardId);

        log.info("Successfully retrieved all accidents of car with Id: [{}].", cardId);

        return retrievedAccidents;
    }

    @Override
    @Transactional
    public void delete(String accidentId) throws AccidentNotFoundException {
        log.info("Deleting accident with Id: [{}].", accidentId);

        accidentRepository.deleteById(accidentId);

        log.info("Successfully deleted accident with Id: [{}].", accidentId);
    }
}
