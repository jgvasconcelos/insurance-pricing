package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.repository.AccidentRepository;
import com.jgvasconcelos.insurancebudget.domain.service.CarService;
import com.jgvasconcelos.insurancebudget.domain.service.DriverService;
import com.jgvasconcelos.insurancebudget.fixture.AccidentFixture;
import com.jgvasconcelos.insurancebudget.resources.repository.accident.exception.AccidentNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
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
public class AccidentServiceImplTest {
    private AccidentRepository accidentRepository;
    private CarService carService;
    private DriverService driverService;
    private AccidentServiceImpl accidentService;

    @BeforeAll
    public void setup() {
        accidentRepository = Mockito.mock(AccidentRepository.class);
        carService = Mockito.mock(CarService.class);
        driverService = Mockito.mock(DriverService.class);
        accidentService = new AccidentServiceImpl(carService, driverService, accidentRepository);
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.reset(accidentRepository);
        Mockito.reset(carService);
        Mockito.reset(driverService);
    }

    @Test
    public void shouldCreateAccidentSuccessfully() throws DriverNotFoundException, CarNotFoundException {
        var accidentToCreate = AccidentFixture.createValidAccident();

        Mockito.when(driverService.getById(accidentToCreate.getDriver().getId())).thenReturn(accidentToCreate.getDriver());
        Mockito.when(carService.getById(accidentToCreate.getCar().getId())).thenReturn(accidentToCreate.getCar());
        Mockito.when(accidentRepository.add(Mockito.any())).thenReturn(accidentToCreate);

        var createdAccident = accidentService.create(accidentToCreate);

        assertEquals(accidentToCreate, createdAccident);
    }

    @Test
    public void shouldFailToCreateAccidentDueToRepositoryException() throws DriverNotFoundException, CarNotFoundException {
        var accidentToCreate = AccidentFixture.createValidAccident();

        Mockito.when(driverService.getById(accidentToCreate.getDriver().getId())).thenReturn(accidentToCreate.getDriver());
        Mockito.when(carService.getById(accidentToCreate.getCar().getId())).thenReturn(accidentToCreate.getCar());
        Mockito.when(accidentRepository.add(Mockito.any())).thenThrow(new PersistenceException());

        var exception = assertThrows(
                PersistenceException.class,
                () -> accidentService.create(accidentToCreate)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }

    @Test
    public void shouldGetAccidentByIdSuccessfully() throws AccidentNotFoundException {
        var accidentToRetrieve = AccidentFixture.createValidAccident();

        Mockito.when(accidentRepository.getById(accidentToRetrieve.getId())).thenReturn(accidentToRetrieve);

        var retrievedAccident = accidentService.getById(accidentToRetrieve.getId());

        assertEquals(accidentToRetrieve, retrievedAccident);
    }

    @Test
    public void shouldGetAllByDriverIdSuccessfully() {
        var accidentToRetrieve = AccidentFixture.createValidAccident();
        var accidentsToRetrieve = List.of(accidentToRetrieve);

        Mockito.when(accidentRepository.getAllByDriverId(accidentToRetrieve.getDriver().getId())).thenReturn(accidentsToRetrieve);

        var retrievedAccidents = accidentService.getAllByDriverId(accidentToRetrieve.getDriver().getId());

        assertEquals(accidentsToRetrieve, retrievedAccidents);
    }

    @Test
    public void shouldGetAllByCarIdSuccessfully() {
        var accidentToRetrieve = AccidentFixture.createValidAccident();
        var accidentsToRetrieve = List.of(accidentToRetrieve);

        Mockito.when(accidentRepository.getAllByCarId(accidentToRetrieve.getCar().getId())).thenReturn(accidentsToRetrieve);

        var retrievedAccidents = accidentService.getAllByCarId(accidentToRetrieve.getCar().getId());

        assertEquals(accidentsToRetrieve, retrievedAccidents);
    }

    @Test
    public void shouldDeleteSuccessfully() throws AccidentNotFoundException {
        var accidentToDelete = AccidentFixture.createValidAccident();

        Mockito.doNothing().when(accidentRepository).deleteById(accidentToDelete.getId());

        accidentService.delete(accidentToDelete.getId());

        Mockito.verify(accidentRepository, Mockito.times(1)).deleteById(accidentToDelete.getId());
    }
}
