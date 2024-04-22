package com.jgvasconcelos.insurancebudget.application.web.controller;

import com.jgvasconcelos.insurancebudget.application.ComponentTest;
import com.jgvasconcelos.insurancebudget.application.web.dto.request.UpdateDriverRequestDto;
import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.domain.repository.DriverRepository;
import com.jgvasconcelos.insurancebudget.fixture.DriverFixture;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.dao.DriverDao;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverAlreadyExistsException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.HttpClientErrorException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DriverControllerComponentTest extends ComponentTest {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DriverDao driverDao;

    @BeforeEach
    public void beforeEach() {
        driverDao.deleteAll();
    }

    @Test
    public void shouldCreateDriverSuccessfully() throws DriverNotFoundException {
        var driverToCreate = DriverFixture.createValidDriverWithoutId();

        var request = RequestEntity.post(assembleUrl("/drivers"))
                .body(driverToCreate);

        var response = restTemplate.exchange(request, Driver.class);

        var createdDriver = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(createdDriver).usingRecursiveComparison()
                .ignoringFields("id", "createdAt", "updatedAt")
                .isEqualTo(driverToCreate);
    }

    @Test
    public void shouldRetrieveDriverByIdSuccessfully() throws DriverAlreadyExistsException {
        var driverToRetrieve = DriverFixture.createValidDriver();

        driverToRetrieve = driverRepository.add(driverToRetrieve);

        var request = RequestEntity.get(assembleUrl("/drivers/" + driverToRetrieve.getId())).build();

        var response = restTemplate.exchange(request, Driver.class);

        var retrievedDriver = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retrievedDriver).usingRecursiveComparison()
                .ignoringFields("createdAt", "updatedAt")
                .isEqualTo(driverToRetrieve);
    }

    @Test
    public void shouldRetrieveDriverByDocumentSuccessfully() throws DriverAlreadyExistsException {
        var driverToRetrieve = DriverFixture.createValidDriver();

        driverToRetrieve = driverRepository.add(driverToRetrieve);

        var request = RequestEntity.get(assembleUrl("/drivers/document/" + driverToRetrieve.getDocument())).build();

        var response = restTemplate.exchange(request, Driver.class);

        var retrievedDriver = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(retrievedDriver).usingRecursiveComparison()
                .ignoringFields("createdAt", "updatedAt")
                .isEqualTo(driverToRetrieve);
    }

    @Test
    public void shouldUpdateDriverSuccessfully() throws DriverAlreadyExistsException {
        var driverToUpdate = DriverFixture.createValidDriver();

        driverToUpdate = driverRepository.add(driverToUpdate);

        var newDriverName = DriverFixture.createValidNewDriverName();

        var requestBody = UpdateDriverRequestDto.builder()
                .name(newDriverName)
                .build();

        var request = RequestEntity.put(assembleUrl("/drivers/" + driverToUpdate.getId()))
                .body(requestBody);

        var response = restTemplate.exchange(request, Driver.class);

        var updatedDriver = response.getBody();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(updatedDriver.getName()).isEqualTo(newDriverName);
    }

    @Test
    public void shouldFailToUpdateDriverBecauseDriverDoesNotExist() throws DriverAlreadyExistsException {
        var driverToUpdate = DriverFixture.createValidDriver();
        var newDriverName = DriverFixture.createValidNewDriverName();

        var requestBody = UpdateDriverRequestDto.builder()
                .name(newDriverName)
                .build();

        var request = RequestEntity.put(assembleUrl("/drivers/" + driverToUpdate.getId()))
                .body(requestBody);

        var exception = assertThrows(
                HttpClientErrorException.class,
                () -> restTemplate.exchange(request, Driver.class)
        );

        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldDeleteDriverByIdSuccessfully() throws DriverAlreadyExistsException {
        var driverToDelete = DriverFixture.createValidDriver();

        driverToDelete = driverRepository.add(driverToDelete);
        final var driverToDeleteId = driverToDelete.getId();

        var request = RequestEntity.delete(assembleUrl("/drivers/" + driverToDeleteId))
                .build();

        var response = restTemplate.exchange(request, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        assertThrows(
                DriverNotFoundException.class,
                () -> driverRepository.getById(driverToDeleteId)
        );
    }

    @Test
    public void shouldFailToDeleteDriverByIdBecauseDriverDoesNotExist() throws DriverAlreadyExistsException {
        var driverToDeleteId = DriverFixture.createValidDriverId();

        var request = RequestEntity.delete(assembleUrl("/drivers/" + driverToDeleteId))
                .build();

        var exception = assertThrows(
                HttpClientErrorException.class,
                () -> restTemplate.exchange(request, Void.class)
        );

        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldDeleteDriverByDocumentSuccessfully() throws DriverAlreadyExistsException {
        var driverToDelete = DriverFixture.createValidDriver();

        driverToDelete = driverRepository.add(driverToDelete);
        final var driverToDeleteDocument = driverToDelete.getDocument();

        var request = RequestEntity.delete(assembleUrl("/drivers/document/" + driverToDeleteDocument))
                .build();

        var response = restTemplate.exchange(request, Void.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        assertThrows(
                DriverNotFoundException.class,
                () -> driverRepository.getByDocument(driverToDeleteDocument)
        );
    }

    @Test
    public void shouldFailToDeleteDriverByDocumentBecauseDriverDoesNotExist() throws DriverAlreadyExistsException {
        var driverToDeleteDocument = DriverFixture.createValidDriverDocument();

        var request = RequestEntity.delete(assembleUrl("/drivers/document/" + driverToDeleteDocument))
                .build();

        var exception = assertThrows(
                HttpClientErrorException.class,
                () -> restTemplate.exchange(request, Void.class)
        );

        assertThat(exception.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    private String assembleUrl(String complementaryUrl) {
        return String.format("http://localhost:%d/api/v1%s", localServerPort, complementaryUrl);
    }
}
