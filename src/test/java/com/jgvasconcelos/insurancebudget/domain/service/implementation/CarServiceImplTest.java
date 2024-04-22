package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.repository.CarRepository;
import com.jgvasconcelos.insurancebudget.fixture.CarFixture;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarServiceImplTest {
    private CarRepository carRepository;
    private CarServiceImpl carService;

    @BeforeAll
    public void setup() {
        carRepository = Mockito.mock(CarRepository.class);
        carService = new CarServiceImpl(carRepository);
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.reset(carRepository);
    }

    @Test
    public void shouldCreateCarSuccessfully() {
        var carToCreate = CarFixture.createValidCar();

        Mockito.when(carRepository.add(carToCreate)).thenReturn(carToCreate);

        var createdCar = carService.create(carToCreate);

        assertEquals(carToCreate, createdCar);
    }

    @Test
    public void shouldFailToCreateCarDueToRepositoryException() {
        var carToCreate = CarFixture.createValidCar();

        Mockito.when(carRepository.add(carToCreate)).thenThrow(new PersistenceException());

        var exception = assertThrows(
                PersistenceException.class,
                () -> carService.create(carToCreate)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }

    @Test
    public void shouldGetCarByIdSuccessfully() throws CarNotFoundException {
        var carToRetrieve = CarFixture.createValidCar();
        var carToRetrieveId = carToRetrieve.getId();

        Mockito.when(carRepository.getById(carToRetrieveId)).thenReturn(carToRetrieve);

        var retrievedCar = carService.getById(carToRetrieveId);

        assertEquals(carToRetrieve, retrievedCar);
    }

    @Test
    public void shouldFailToGetCarByIdBecauseCarDoesNotExist() throws CarNotFoundException {
        var carToRetrieveId = CarFixture.createValidCarId();

        Mockito.when(carRepository.getById(carToRetrieveId)).thenThrow(new CarNotFoundException("Car not found"));

        var exception = assertThrows(
                CarNotFoundException.class,
                () -> carService.getById(carToRetrieveId)
        );

        assertEquals(CarNotFoundException.class, exception.getClass());
    }

    @Test
    public void shouldFailToGetCarByIdDueToRepositoryException() throws CarNotFoundException {
        var carToRetrieveId = CarFixture.createValidCarId();

        Mockito.when(carRepository.getById(carToRetrieveId)).thenThrow(new PersistenceException());

        var exception = assertThrows(
                PersistenceException.class,
                () -> carService.getById(carToRetrieveId)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }

    @Test
    public void shouldUpdateCarFipeValueByIdSuccessfully() throws CarNotFoundException {
        var carToUpdateFipeValue = CarFixture.createValidCar();
        var carToUpdateFipeValueId = carToUpdateFipeValue.getId();

        var newFipeValue = CarFixture.createvalidNewFipeValue();
        var carWithNewFipeValue = CarFixture.createValidCarWithUpdatedFipeValue(carToUpdateFipeValue, newFipeValue);

        Mockito.when(carRepository.updateFipeValueById(carToUpdateFipeValueId, newFipeValue)).thenReturn(carWithNewFipeValue);

        var carWithUpdatedFipeValue = carService.updateFipeValueById(carToUpdateFipeValueId, newFipeValue);

        assertEquals(carWithNewFipeValue, carWithUpdatedFipeValue);
    }

    @Test
    public void shouldFailToUpdateCarFipeValueByIdBecauseCarDoesNotExist() throws CarNotFoundException {
        var carToRetrieveId = CarFixture.createValidCarId();
        var newFipeValue = CarFixture.createvalidNewFipeValue();

        Mockito.when(carRepository.updateFipeValueById(carToRetrieveId, newFipeValue)).thenThrow(new CarNotFoundException("Car not found"));

        var exception = assertThrows(
                CarNotFoundException.class,
                () -> carService.updateFipeValueById(carToRetrieveId, newFipeValue)
        );

        assertEquals(CarNotFoundException.class, exception.getClass());
    }

    @Test
    public void shouldFailToUpdateCarFipeValueByIdDueToRepositoryException() throws CarNotFoundException {
        var carToRetrieveId = CarFixture.createValidCarId();
        var newFipeValue = CarFixture.createvalidNewFipeValue();

        Mockito.when(carRepository.updateFipeValueById(carToRetrieveId, newFipeValue)).thenThrow(new PersistenceException());

        var exception = assertThrows(
                PersistenceException.class,
                () -> carService.updateFipeValueById(carToRetrieveId, newFipeValue)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }

    @Test
    public void shouldDeleteCarByIdSuccessfully() throws CarNotFoundException {
        var carToDeleteId = CarFixture.createValidCarId();

        Mockito.doNothing().when(carRepository).deleteById(carToDeleteId);

        carService.deleteById(carToDeleteId);

        Mockito.verify(carRepository, Mockito.times(1)).deleteById(carToDeleteId);
    }

    @Test
    public void shouldFailToDeleteCarByIdBecauseCarDoesNotExist() throws CarNotFoundException {
        var carToDeleteId = CarFixture.createValidCarId();

        Mockito.doThrow(new CarNotFoundException("Car not found")).when(carRepository).deleteById(carToDeleteId);

        var exception = assertThrows(
                CarNotFoundException.class,
                () -> carService.deleteById(carToDeleteId)
        );

        assertEquals(CarNotFoundException.class, exception.getClass());
    }

    @Test
    public void shouldFailToDeleteCarByIdDueToRepositoryException() throws CarNotFoundException {
        var carToDeleteId = CarFixture.createValidCarId();

        Mockito.doThrow(new PersistenceException()).when(carRepository).deleteById(carToDeleteId);

        var exception = assertThrows(
                PersistenceException.class,
                () -> carService.deleteById(carToDeleteId)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }
}
