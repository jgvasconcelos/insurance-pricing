package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.repository.CarDriverRepository;
import com.jgvasconcelos.insurancebudget.domain.service.CarService;
import com.jgvasconcelos.insurancebudget.domain.service.DriverService;
import com.jgvasconcelos.insurancebudget.fixture.CarDriverFixture;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.cardriver.exception.CarDriverNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CarDriverServiceImplTest {
    private DriverService driverService;
    private CarService carService;
    private CarDriverRepository carDriverRepository;
    private CarDriverServiceImpl carDriverService;

    @BeforeAll
    public void setup() {
        driverService = Mockito.mock(DriverService.class);
        carService = Mockito.mock(CarService.class);
        carDriverRepository = Mockito.mock(CarDriverRepository.class);
        carDriverService = new CarDriverServiceImpl(driverService, carService, carDriverRepository);
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.reset(driverService);
        Mockito.reset(carService);
        Mockito.reset(carDriverRepository);
    }

    @Test
    public void shouldCreateCarDriverSuccessfully() throws DriverNotFoundException, CarNotFoundException {
        var carDriverToCreate = CarDriverFixture.createValidCarDriver();

        Mockito.when(driverService.getById(carDriverToCreate.getDriver().getId())).thenReturn(carDriverToCreate.getDriver());
        Mockito.when(carService.getById(carDriverToCreate.getCar().getId())).thenReturn(carDriverToCreate.getCar());
        Mockito.when(carDriverRepository.add(Mockito.any())).thenReturn(carDriverToCreate);

        var createdCarDriver = carDriverService.create(carDriverToCreate);

        assertEquals(carDriverToCreate, createdCarDriver);
    }

    @Test
    public void shouldFailToCreateCarDriverDueToRepositoryException() throws DriverNotFoundException, CarNotFoundException {
        var carDriverToCreate = CarDriverFixture.createValidCarDriver();

        Mockito.when(driverService.getById(carDriverToCreate.getDriver().getId())).thenReturn(carDriverToCreate.getDriver());
        Mockito.when(carService.getById(carDriverToCreate.getCar().getId())).thenReturn(carDriverToCreate.getCar());
        Mockito.when(carDriverRepository.add(Mockito.any())).thenThrow(new PersistenceException());

        var exception = assertThrows(
                PersistenceException.class,
                () -> carDriverService.create(carDriverToCreate)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }

    @Test
    public void shouldGetCarDriverByIdSuccessfully() throws CarDriverNotFoundException {
        var carDriverToRetrieve = CarDriverFixture.createValidCarDriver();

        Mockito.when(carDriverRepository.getById(carDriverToRetrieve.getId())).thenReturn(carDriverToRetrieve);

        var retrievedCarDriver = carDriverService.getById(carDriverToRetrieve.getId());

        assertEquals(carDriverToRetrieve, retrievedCarDriver);
    }

    @Test
    public void shouldGetCarDriversByDriverIdSuccessfully(){
        var carDriverToRetrieve = CarDriverFixture.createValidCarDriver();
        var carDriversToRetrieve = List.of(carDriverToRetrieve);

        Mockito.when(carDriverRepository.getAllByDriverId(carDriverToRetrieve.getDriver().getId())).thenReturn(carDriversToRetrieve);

        var retrievedCarDrivers = carDriverService.getAllByDriverId(carDriverToRetrieve.getDriver().getId());

        assertEquals(carDriversToRetrieve, retrievedCarDrivers);
    }

    @Test
    public void shouldGetCarDriversByCarIdSuccessfully(){
        var carDriverToRetrieve = CarDriverFixture.createValidCarDriver();
        var carDriversToRetrieve = List.of(carDriverToRetrieve);

        Mockito.when(carDriverRepository.getAllByCarId(carDriverToRetrieve.getCar().getId())).thenReturn(carDriversToRetrieve);

        var retrievedCarDrivers = carDriverService.getAllByCarId(carDriverToRetrieve.getCar().getId());

        assertEquals(carDriversToRetrieve, retrievedCarDrivers);
    }

    @Test
    public void shouldDeleteCarDriverByIdSuccessfully() throws CarDriverNotFoundException, CarNotFoundException {
        var carDriverToDelete = CarDriverFixture.createValidCarDriver();

        Mockito.doNothing().when(carDriverRepository).deleteById(carDriverToDelete.getId());

        carService.deleteById(carDriverToDelete.getId());

        Mockito.verify(carDriverRepository, Mockito.times(1)).deleteById(carDriverToDelete.getId());
    }
}
