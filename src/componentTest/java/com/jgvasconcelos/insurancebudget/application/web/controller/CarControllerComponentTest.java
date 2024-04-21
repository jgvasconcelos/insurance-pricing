package com.jgvasconcelos.insurancebudget.application.web.controller;

import com.jgvasconcelos.insurancebudget.application.ComponentTest;
import com.jgvasconcelos.insurancebudget.application.web.dto.request.UpdateCarFipeValueRequestDto;
import com.jgvasconcelos.insurancebudget.application.web.exception.dto.ApiErrorResponseDto;
import com.jgvasconcelos.insurancebudget.domain.model.car.Car;
import com.jgvasconcelos.insurancebudget.domain.repository.CarRepository;
import com.jgvasconcelos.insurancebudget.fixture.CarFixture;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarControllerComponentTest extends ComponentTest {
    @Autowired
    private CarRepository carRepository;

    @Test
    public void shouldCreateCarSuccessfully() throws CarNotFoundException {
        var carToCreate = CarFixture.createValidCarWithoutId();

        var request = RequestEntity.post(assembleUrl("/cars"))
                .body(carToCreate);

        var response = restTemplate.exchange(request, Car.class);

        var createdCar = response.getBody();

        var carSavedOnRepository = carRepository.getById(createdCar.getId());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(carSavedOnRepository).usingRecursiveComparison()
                .ignoringFields("createdAt", "updatedAt")
                .isEqualTo(createdCar);
    }

    @Test
    public void shouldRetrieveCreatedCarSuccessfully() {
        var carToRetrieve = CarFixture.createValidCar();

        carToRetrieve = carRepository.add(carToRetrieve);

        var request = RequestEntity.get(assembleUrl("/cars/" + carToRetrieve.getId())).build();

        var response = restTemplate.exchange(request, Car.class);

        var retrievedCar = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retrievedCar).usingRecursiveComparison()
                .ignoringFields("createdAt", "updatedAt")
                .isEqualTo(carToRetrieve);
    }

    @Test
    public void shouldFailToRetrieveCarBecauseCarDoesNotExist() {
        var carIdToRetrieve = CarFixture.createValidCarId();

        var request = RequestEntity.get(assembleUrl("/cars/" + carIdToRetrieve)).build();

        var exception = assertThrows(
                HttpClientErrorException.class,
                () -> restTemplate.exchange(request, ApiErrorResponseDto.class)
        );

        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldUpdateCarFipeValueSuccessfully() {
        var carToUpdateFipeValue = CarFixture.createValidCar();

        carToUpdateFipeValue = carRepository.add(carToUpdateFipeValue);

        var newFipeValue = CarFixture.createvalidNewFipeValue();

        var requestBody = new UpdateCarFipeValueRequestDto(newFipeValue);

        var request = RequestEntity.patch(assembleUrl("/cars/" + carToUpdateFipeValue.getId()))
                .body(requestBody);

        var response = restTemplate.exchange(request, Car.class);

        var carWithUpdatedFipeValue = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(carWithUpdatedFipeValue.getFipeValue()).isEqualTo(newFipeValue);
    }

    @Test
    public void shouldFailToUpdateCarFipeValueBecauseCarDoesNotExist() {
        var carToUpdateFipeValueId = CarFixture.createValidCarId();
        var newFipeValue = CarFixture.createvalidNewFipeValue();

        var requestBody = new UpdateCarFipeValueRequestDto(newFipeValue);

        var request = RequestEntity.patch(assembleUrl("/cars/" + carToUpdateFipeValueId))
                .body(requestBody);

        var exception = assertThrows(
                HttpClientErrorException.class,
                () -> restTemplate.exchange(request, ApiErrorResponseDto.class)
        );

        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldDeleteCarSuccessfully() {
        var carToDelete = CarFixture.createValidCar();

        carToDelete = carRepository.add(carToDelete);
        final var carToDeleteId = carToDelete.getId();

        var request = RequestEntity.delete(assembleUrl("/cars/" + carToDeleteId))
                .build();

        var response = restTemplate.exchange(request, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        assertThrows(
                CarNotFoundException.class,
                () -> carRepository.getById(carToDeleteId)
        );
    }

    @Test
    public void shouldFailToDeleteCarBecauseCarDoesNotExist() {
        var carToDeleteId = CarFixture.createValidCarId();

        var request = RequestEntity.delete(assembleUrl("/cars/" + carToDeleteId))
                .build();

        var apiException = assertThrows(
                HttpClientErrorException.class,
                () -> restTemplate.exchange(request, Void.class)
        );

        assertThat(apiException.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private String assembleUrl(String complementaryUrl) {
        return String.format("http://localhost:%d/api/v1%s", localServerPort, complementaryUrl);
    }
}
