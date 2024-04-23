package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.exception.DriverIsAgeMinor;
import com.jgvasconcelos.insurancebudget.domain.repository.DriverRepository;
import com.jgvasconcelos.insurancebudget.fixture.DriverFixture;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverAlreadyExistsException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DriverServiceImplTest {
    private DriverRepository driverRepository;
    private DriverServiceImpl driverService;

    @BeforeAll
    public void setup() {
        driverRepository = Mockito.mock(DriverRepository.class);
        driverService = new DriverServiceImpl(driverRepository);
    }

    @BeforeEach
    public void beforeEach() {
        Mockito.reset(driverRepository);
    }

    @Test
    public void shouldCreateDriverSuccessfully() throws DriverAlreadyExistsException, DriverIsAgeMinor {
        var driverToCreate = DriverFixture.createValidDriver();

        Mockito.when(driverRepository.add(driverToCreate)).thenReturn(driverToCreate);

        var createdDriver = driverService.createDriver(driverToCreate);

        assertEquals(driverToCreate, createdDriver);
    }

    @Test
    public void shouldFailToCreateDriverBecauseDriverAlreadyExists() throws DriverAlreadyExistsException {
        var driverToCreate = DriverFixture.createValidDriver();

        Mockito.when(driverRepository.add(driverToCreate)).thenThrow(new DriverAlreadyExistsException("Driver already exists"));

        var exception = assertThrows(
                DriverAlreadyExistsException.class,
                () -> driverService.createDriver(driverToCreate)
        );

        assertEquals(DriverAlreadyExistsException.class, exception.getClass());
    }

    @Test
    public void shouldFailToCreateDriverDueToRepositoryException() throws DriverAlreadyExistsException {
        var driverToCreate = DriverFixture.createValidDriver();

        Mockito.when(driverRepository.add(driverToCreate)).thenThrow(new PersistenceException());

        var exception = assertThrows(
                PersistenceException.class,
                () -> driverService.createDriver(driverToCreate)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }

    @Test
    public void shouldGetDriverByIdSuccessfully() throws DriverNotFoundException {
        var driverToRetrieve = DriverFixture.createValidDriver();
        var driverToRetrieveId = driverToRetrieve.getId();

        Mockito.when(driverRepository.getById(driverToRetrieveId)).thenReturn(driverToRetrieve);

        var retrievedDriver = driverService.getById(driverToRetrieveId);

        assertEquals(driverToRetrieve, retrievedDriver);
    }

    @Test
    public void shouldFailToGetDriverByIdBecauseDriverDoesNotExist() throws DriverNotFoundException {
        var driverToRetrieveId = DriverFixture.createValidDriverId();

        Mockito.when(driverRepository.getById(driverToRetrieveId)).thenThrow(new DriverNotFoundException("Driver not found"));

        var exception = assertThrows(
                DriverNotFoundException.class,
                () -> driverService.getById(driverToRetrieveId)
        );

        assertEquals(DriverNotFoundException.class, exception.getClass());
    }

    @Test
    public void shouldFailToGetDriverByIdDueToRepositoryException() throws DriverNotFoundException {
        var driverToRetrieveId = DriverFixture.createValidDriverId();

        Mockito.when(driverRepository.getById(driverToRetrieveId)).thenThrow(new PersistenceException());

        var exception = assertThrows(
                PersistenceException.class,
                () -> driverService.getById(driverToRetrieveId)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }

    @Test
    public void shouldGetDriverByDocumentSuccessfully() throws DriverNotFoundException {
        var driverToRetrieve = DriverFixture.createValidDriver();
        var driverToRetrieveDocument = driverToRetrieve.getDocument();

        Mockito.when(driverRepository.getByDocument(driverToRetrieveDocument)).thenReturn(driverToRetrieve);

        var retrievedDriver = driverService.getByDocument(driverToRetrieveDocument);

        assertEquals(driverToRetrieve, retrievedDriver);
    }

    @Test
    public void shouldFailToGetDriverByDocumentBecauseDriverDoesNotExist() throws DriverNotFoundException {
        var driverToRetrieveDocument = DriverFixture.createValidDriverDocument();

        Mockito.when(driverRepository.getByDocument(driverToRetrieveDocument)).thenThrow(new DriverNotFoundException("Driver not found"));

        var exception = assertThrows(
                DriverNotFoundException.class,
                () -> driverService.getByDocument(driverToRetrieveDocument)
        );

        assertEquals(DriverNotFoundException.class, exception.getClass());
    }

    @Test
    public void shouldFailToGetDriverByDocumentDueToRepositoryException() throws DriverNotFoundException {
        var driverToRetrieveDocument = DriverFixture.createValidDriverDocument();

        Mockito.when(driverRepository.getByDocument(driverToRetrieveDocument)).thenThrow(new PersistenceException());

        var exception = assertThrows(
                PersistenceException.class,
                () -> driverService.getByDocument(driverToRetrieveDocument)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }

    @Test
    public void shouldUpdateDriverSuccessfully() throws DriverNotFoundException {
        var driverToUpdate = DriverFixture.createValidDriver();

        Mockito.when(driverRepository.getById(driverToUpdate.getId())).thenReturn(driverToUpdate);
        Mockito.when(driverRepository.updateDriver(driverToUpdate)).thenReturn(driverToUpdate);

        var updatedDriver = driverService.updateDriver(driverToUpdate);

        assertEquals(driverToUpdate, updatedDriver);
    }

    @Test
    public void shouldFailToUpdateDriverBecauseDriverDoesNotExist() throws DriverNotFoundException {
        var driverToUpdate = DriverFixture.createValidDriver();

        Mockito.when(driverRepository.getById(driverToUpdate.getId())).thenReturn(driverToUpdate);
        Mockito.when(driverRepository.updateDriver(driverToUpdate)).thenThrow(new DriverNotFoundException("Driver not found"));

        var exception = assertThrows(
                DriverNotFoundException.class,
                () -> driverService.updateDriver(driverToUpdate)
        );

        assertEquals(DriverNotFoundException.class, exception.getClass());
    }

    @Test
    public void shouldFailToUpdateDriverDueToPersistenceException() throws DriverNotFoundException {
        var driverToUpdate = DriverFixture.createValidDriver();

        Mockito.when(driverRepository.getById(driverToUpdate.getId())).thenReturn(driverToUpdate);
        Mockito.when(driverRepository.updateDriver(driverToUpdate)).thenThrow(new PersistenceException());

        var exception = assertThrows(
                PersistenceException.class,
                () -> driverService.updateDriver(driverToUpdate)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }

    @Test
    public void shouldDeleteDriverByIdSuccessfully() throws DriverNotFoundException {
        var driverToDeleteId = DriverFixture.createValidDriverId();

        Mockito.doNothing().when(driverRepository).deleteById(driverToDeleteId);

        driverService.deleteById(driverToDeleteId);

        Mockito.verify(driverRepository, Mockito.times(1)).deleteById(driverToDeleteId);
    }

    @Test
    public void shouldFailToDeleteDriverByIdBecauseDriverDoesNotExist() throws DriverNotFoundException {
        var driverToDeleteId = DriverFixture.createValidDriverId();

        Mockito.doThrow(new DriverNotFoundException("Driver not found")).when(driverRepository).deleteById(driverToDeleteId);

        var exception = assertThrows(
                DriverNotFoundException.class,
                () -> driverService.deleteById(driverToDeleteId)
        );

        assertEquals(DriverNotFoundException.class, exception.getClass());
    }

    @Test
    public void shouldFailToDeleteDriverByIdDueToPersistenceException() throws DriverNotFoundException {
        var driverToDeleteId = DriverFixture.createValidDriverId();

        Mockito.doThrow(new PersistenceException()).when(driverRepository).deleteById(driverToDeleteId);

        var exception = assertThrows(
                PersistenceException.class,
                () -> driverService.deleteById(driverToDeleteId)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }

    @Test
    public void shouldDeleteDriverByDocumentSuccessfully() throws DriverNotFoundException {
        var driverToDeleteDocument = DriverFixture.createValidDriverDocument();

        Mockito.doNothing().when(driverRepository).deleteByDocument(driverToDeleteDocument);

        driverService.deleteByDocument(driverToDeleteDocument);

        Mockito.verify(driverRepository, Mockito.times(1)).deleteByDocument(driverToDeleteDocument);
    }

    @Test
    public void shouldFailToDeleteDriverByDocumentBecauseDriverDoesNotExist() throws DriverNotFoundException {
        var driverToDeleteDocument = DriverFixture.createValidDriverDocument();

        Mockito.doThrow(new DriverNotFoundException("Driver not found")).when(driverRepository).deleteByDocument(driverToDeleteDocument);

        var exception = assertThrows(
                DriverNotFoundException.class,
                () -> driverService.deleteByDocument(driverToDeleteDocument)
        );

        assertEquals(DriverNotFoundException.class, exception.getClass());
    }

    @Test
    public void shouldFailToDeleteDriverByDocumentDueToPersistenceException() throws DriverNotFoundException {
        var driverToDeleteDocument = DriverFixture.createValidDriverDocument();

        Mockito.doThrow(new PersistenceException()).when(driverRepository).deleteByDocument(driverToDeleteDocument);

        var exception = assertThrows(
                PersistenceException.class,
                () -> driverService.deleteByDocument(driverToDeleteDocument)
        );

        assertEquals(PersistenceException.class, exception.getClass());
    }
}
