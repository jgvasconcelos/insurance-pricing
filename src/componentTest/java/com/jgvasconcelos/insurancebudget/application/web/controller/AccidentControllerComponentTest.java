package com.jgvasconcelos.insurancebudget.application.web.controller;

import com.jgvasconcelos.insurancebudget.application.ComponentTest;
import com.jgvasconcelos.insurancebudget.application.web.dto.request.CreateAccidentRequestDto;
import com.jgvasconcelos.insurancebudget.domain.model.Accident;
import com.jgvasconcelos.insurancebudget.domain.repository.AccidentRepository;
import com.jgvasconcelos.insurancebudget.domain.repository.CarRepository;
import com.jgvasconcelos.insurancebudget.domain.repository.DriverRepository;
import com.jgvasconcelos.insurancebudget.fixture.AccidentFixture;
import com.jgvasconcelos.insurancebudget.resources.repository.accident.dao.AccidentDao;
import com.jgvasconcelos.insurancebudget.resources.repository.car.dao.CarDao;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.dao.DriverDao;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class AccidentControllerComponentTest extends ComponentTest {
    @Autowired
    private AccidentRepository accidentRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarDao carDao;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverDao driverDao;
    @Autowired
    private AccidentDao accidentDao;

    @BeforeEach
    public void beforeEach() {
        accidentDao.deleteAll();
        driverDao.deleteAll();
        carDao.deleteAll();
    }

    @Test
    public void shouldCreateAccidentSuccessfully() throws DriverAlreadyExistsException {
        var accidentToCreate = AccidentFixture.createValidAccident();

        var car = carRepository.add(accidentToCreate.getCar());
        var driver = driverRepository.add(accidentToCreate.getDriver());

        accidentToCreate.setCar(car);
        accidentToCreate.setDriver(driver);

        var requestBody = CreateAccidentRequestDto.builder()
                .carId(accidentToCreate.getCar().getId())
                .driverId(accidentToCreate.getDriver().getId())
                .accidentDate(accidentToCreate.getAccidentDate())
                .build();

        var request = RequestEntity.post(assembleUrl("/accidents"))
                .body(requestBody);

        var response = restTemplate.exchange(request, Accident.class);

        var createdAccident = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createdAccident).usingRecursiveComparison()
                .ignoringFields("id", "car", "driver", "createdAt", "updatedAt")
                .isEqualTo(accidentToCreate);
    }

    @Test
    public void shouldGetAccidentByIdSuccessfully() throws DriverAlreadyExistsException {
        var accidentToRetrieve = AccidentFixture.createValidAccident();

        var car = carRepository.add(accidentToRetrieve.getCar());
        var driver = driverRepository.add(accidentToRetrieve.getDriver());

        accidentToRetrieve.setCar(car);
        accidentToRetrieve.setDriver(driver);

        accidentToRetrieve = accidentRepository.add(accidentToRetrieve);

        var request = RequestEntity.get(assembleUrl("/accidents/" + accidentToRetrieve.getId())).build();

        var response = restTemplate.exchange(request, Accident.class);

        var retrievedAccident = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retrievedAccident).usingRecursiveComparison()
                .ignoringFields("id", "car", "driver", "createdAt", "updatedAt")
                .isEqualTo(accidentToRetrieve);
    }

    @Test
    public void shouldRetrieveAccidentsByDriverIdSuccessfully() throws DriverAlreadyExistsException {
        var accidentToRetrieve = AccidentFixture.createValidAccident();

        var car = carRepository.add(accidentToRetrieve.getCar());
        var driver = driverRepository.add(accidentToRetrieve.getDriver());

        accidentToRetrieve.setCar(car);
        accidentToRetrieve.setDriver(driver);

        accidentToRetrieve = accidentRepository.add(accidentToRetrieve);

        var request = RequestEntity.get(assembleUrl("/accidents/driver/" + driver.getId())).build();

        var response = restTemplate.exchange(request, Accident[].class);

        var retrievedAccidents = Arrays.stream(response.getBody()).toList();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retrievedAccidents.get(0)).usingRecursiveComparison()
                .ignoringFields("id", "car", "driver", "createdAt", "updatedAt")
                .isEqualTo(accidentToRetrieve);
    }

    @Test
    public void shouldRetrieveAccidentsByCarIdSuccessfully() throws DriverAlreadyExistsException {
        var accidentToRetrieve = AccidentFixture.createValidAccident();

        var car = carRepository.add(accidentToRetrieve.getCar());
        var driver = driverRepository.add(accidentToRetrieve.getDriver());

        accidentToRetrieve.setCar(car);
        accidentToRetrieve.setDriver(driver);

        accidentToRetrieve = accidentRepository.add(accidentToRetrieve);

        var request = RequestEntity.get(assembleUrl("/accidents/car/" + car.getId())).build();

        var response = restTemplate.exchange(request, Accident[].class);

        var retrievedAccidents = Arrays.stream(response.getBody()).toList();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retrievedAccidents.get(0)).usingRecursiveComparison()
                .ignoringFields("id", "car", "driver", "createdAt", "updatedAt")
                .isEqualTo(accidentToRetrieve);
    }

    @Test
    public void shouldDeleteAccidentsByIdSuccessfully() throws DriverAlreadyExistsException {
        var accidentToDelete = AccidentFixture.createValidAccident();

        var car = carRepository.add(accidentToDelete.getCar());
        var driver = driverRepository.add(accidentToDelete.getDriver());

        accidentToDelete.setCar(car);
        accidentToDelete.setDriver(driver);

        accidentToDelete = accidentRepository.add(accidentToDelete);

        var request = RequestEntity.delete(assembleUrl("/accidents/" + accidentToDelete.getId())).build();

        var response = restTemplate.exchange(request, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    private String assembleUrl(String complementaryUrl) {
        return String.format("http://localhost:%d/api/v1%s", localServerPort, complementaryUrl);
    }
}
