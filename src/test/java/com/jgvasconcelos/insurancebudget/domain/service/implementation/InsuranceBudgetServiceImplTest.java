package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.exception.DriverIsNotAssociatedWithCarException;
import com.jgvasconcelos.insurancebudget.domain.exception.DriverIsNotMainDriverOfCarException;
import com.jgvasconcelos.insurancebudget.domain.repository.InsuranceBudgetRepository;
import com.jgvasconcelos.insurancebudget.domain.service.CarDriverService;
import com.jgvasconcelos.insurancebudget.domain.service.CarService;
import com.jgvasconcelos.insurancebudget.domain.service.DriverService;
import com.jgvasconcelos.insurancebudget.fixture.CarDriverFixture;
import com.jgvasconcelos.insurancebudget.fixture.InsuranceBudgetFixture;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.exception.InsuranceBudgetNotFoundException;
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
public class InsuranceBudgetServiceImplTest {
    private DriverService driverService;
    private CarService carService;
    private CarDriverService carDriverService;
    private InsuranceBudgetRepository insuranceBudgetRepository;
    private InsuranceBudgetServiceImpl insuranceBudgetService;

    @BeforeAll
    public void setup() {
        driverService = Mockito.mock(DriverService.class);
        carService = Mockito.mock(CarService.class);
        carDriverService = Mockito.mock(CarDriverService.class);
        insuranceBudgetRepository = Mockito.mock(InsuranceBudgetRepository.class);
        insuranceBudgetService = new InsuranceBudgetServiceImpl(driverService, carService, carDriverService, insuranceBudgetRepository);
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.reset(driverService);
        Mockito.reset(carService);
        Mockito.reset(carDriverService);
        Mockito.reset(insuranceBudgetRepository);
    }

    @Test
    public void shouldCreateInsuranceBudgetSuccessfully() throws DriverNotFoundException, CarNotFoundException, DriverIsNotMainDriverOfCarException, DriverIsNotAssociatedWithCarException {
        var insuranceBudgetToCreate = InsuranceBudgetFixture.createValidInsuranceBudget();

        var carDriver = CarDriverFixture.createValidCarDriver();
        carDriver.setCar(insuranceBudgetToCreate.getCar());
        carDriver.setDriver(insuranceBudgetToCreate.getDriver());
        var carDrivers = List.of(carDriver);

        Mockito.when(driverService.getById(insuranceBudgetToCreate.getDriver().getId())).thenReturn(insuranceBudgetToCreate.getDriver());
        Mockito.when(carService.getById(insuranceBudgetToCreate.getCar().getId())).thenReturn(insuranceBudgetToCreate.getCar());
        Mockito.when(carDriverService.getAllByDriverId(insuranceBudgetToCreate.getDriver().getId())).thenReturn(carDrivers);
        Mockito.when(insuranceBudgetRepository.add(Mockito.any())).thenReturn(insuranceBudgetToCreate);

        var createdInsuranceBudget = insuranceBudgetService.createInsuranceBudget(insuranceBudgetToCreate);

        assertEquals(insuranceBudgetToCreate, createdInsuranceBudget);
    }

    @Test
    public void shouldFailToCreateInsuranceBudgetDueToRepositoryException() throws DriverNotFoundException, CarNotFoundException {
        var insuranceBudgetToCreate = InsuranceBudgetFixture.createValidInsuranceBudget();

        var carDriver = CarDriverFixture.createValidCarDriver();
        carDriver.setCar(insuranceBudgetToCreate.getCar());
        carDriver.setDriver(insuranceBudgetToCreate.getDriver());
        var carDrivers = List.of(carDriver);

        Mockito.when(driverService.getById(insuranceBudgetToCreate.getDriver().getId())).thenReturn(insuranceBudgetToCreate.getDriver());
        Mockito.when(carService.getById(insuranceBudgetToCreate.getCar().getId())).thenReturn(insuranceBudgetToCreate.getCar());
        Mockito.when(carDriverService.getAllByDriverId(insuranceBudgetToCreate.getDriver().getId())).thenReturn(carDrivers);
        Mockito.when(insuranceBudgetRepository.add(Mockito.any())).thenThrow(new PersistenceException());

        var exception = assertThrows(
                PersistenceException.class,
                () -> insuranceBudgetService.createInsuranceBudget(insuranceBudgetToCreate)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }

    @Test
    public void shouldGetInsuranceBudgetByIdSuccessfully() throws InsuranceBudgetNotFoundException {
        var insuranceBudgetToRetrieve = InsuranceBudgetFixture.createValidInsuranceBudget();

        Mockito.when(insuranceBudgetRepository.getById(insuranceBudgetToRetrieve.getId())).thenReturn(insuranceBudgetToRetrieve);

        var retrievedInsuranceBudget = insuranceBudgetService.getById(insuranceBudgetToRetrieve.getId());

        assertEquals(insuranceBudgetToRetrieve, retrievedInsuranceBudget);
    }

    @Test
    public void shouldGetAllInsuranceBudgetsByDriverId() {
        var insuranceBudgetToRetrieve = InsuranceBudgetFixture.createValidInsuranceBudget();
        var insuranceBudgetsToRetrieve = List.of(insuranceBudgetToRetrieve);

        Mockito.when(insuranceBudgetRepository.getByDriverId(insuranceBudgetToRetrieve.getDriver().getId())).thenReturn(insuranceBudgetsToRetrieve);

        var retrievedInsuranceBudgets = insuranceBudgetService.getByDriverId(insuranceBudgetToRetrieve.getDriver().getId());

        assertEquals(insuranceBudgetsToRetrieve, retrievedInsuranceBudgets);
    }

    @Test
    public void shouldGetAllInsuranceBudgetsByCarId() {
        var insuranceBudgetToRetrieve = InsuranceBudgetFixture.createValidInsuranceBudget();
        var insuranceBudgetsToRetrieve = List.of(insuranceBudgetToRetrieve);

        Mockito.when(insuranceBudgetRepository.getByCarId(insuranceBudgetToRetrieve.getCar().getId())).thenReturn(insuranceBudgetsToRetrieve);

        var retrievedInsuranceBudgets = insuranceBudgetService.getByCarId(insuranceBudgetToRetrieve.getCar().getId());

        assertEquals(insuranceBudgetsToRetrieve, retrievedInsuranceBudgets);
    }

    @Test
    public void shouldUpdateInsuranceBudgetSuccessfully() throws DriverNotFoundException, CarNotFoundException, InsuranceBudgetNotFoundException {
        var insuranceBudgetToUpdate = InsuranceBudgetFixture.createValidInsuranceBudget();

        Mockito.when(driverService.getById(insuranceBudgetToUpdate.getDriver().getId())).thenReturn(insuranceBudgetToUpdate.getDriver());
        Mockito.when(carService.getById(insuranceBudgetToUpdate.getCar().getId())).thenReturn(insuranceBudgetToUpdate.getCar());
        Mockito.when(insuranceBudgetRepository.getById(insuranceBudgetToUpdate.getId())).thenReturn(insuranceBudgetToUpdate);
        Mockito.when(insuranceBudgetRepository.updateInsuranceBudget(Mockito.any())).thenReturn(insuranceBudgetToUpdate);

        var updatedInsuranceBudget = insuranceBudgetService.updateInsuranceBudget(insuranceBudgetToUpdate);

        assertEquals(insuranceBudgetToUpdate, updatedInsuranceBudget);
    }

    @Test
    public void shouldDeleteInsuranceBudgetSuccessfully() throws InsuranceBudgetNotFoundException {
        var insuranceBudgetToDelete = InsuranceBudgetFixture.createValidInsuranceBudget();

        Mockito.doNothing().when(insuranceBudgetRepository).deleteById(insuranceBudgetToDelete.getId());

        insuranceBudgetService.deleteById(insuranceBudgetToDelete.getId());

        Mockito.verify(insuranceBudgetRepository, Mockito.times(1)).deleteById(insuranceBudgetToDelete.getId());
    }
}
