package com.jgvasconcelos.insurancebudget.application.web.controller;

import com.jgvasconcelos.insurancebudget.application.web.dto.request.CreateDriverRequestDto;
import com.jgvasconcelos.insurancebudget.application.web.dto.request.UpdateDriverRequestDto;
import com.jgvasconcelos.insurancebudget.application.web.dto.response.DriverResponseDto;
import com.jgvasconcelos.insurancebudget.domain.model.Driver;
import com.jgvasconcelos.insurancebudget.domain.service.DriverService;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverAlreadyExistsException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/drivers")
public class DriverController {
    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<DriverResponseDto> createDriver(
            @Valid @RequestBody CreateDriverRequestDto createDriverRequest
    ) throws DriverAlreadyExistsException {
        Driver createdDriver = driverService.createDriver(createDriverRequest.toModel());

        DriverResponseDto driverResponseBody = DriverResponseDto.fromModel(createdDriver);

        return ResponseEntity.status(HttpStatus.CREATED).body(driverResponseBody);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<DriverResponseDto> getDriverById(
            @PathVariable String driverId
    ) throws DriverNotFoundException {
        Driver retrievedDriver = driverService.getById(driverId);

        DriverResponseDto getDriverResponseBody = DriverResponseDto.fromModel(retrievedDriver);

        return ResponseEntity.status(HttpStatus.OK).body(getDriverResponseBody);
    }

    @GetMapping("/document/{driverDocument}")
    public ResponseEntity<DriverResponseDto> getDriverByDocument(
            @PathVariable String driverDocument
    ) throws DriverNotFoundException {
        Driver retrievedDriver = driverService.getByDocument(driverDocument);

        DriverResponseDto getDriverResponseBody = DriverResponseDto.fromModel(retrievedDriver);

        return ResponseEntity.status(HttpStatus.OK).body(getDriverResponseBody);
    }

    @PutMapping("/{driverId}")
    public ResponseEntity<DriverResponseDto> updateDriver(
            @PathVariable String driverId,
            @Valid @RequestBody UpdateDriverRequestDto updateDriverRequest
    ) throws DriverNotFoundException {
        Driver driverToUpdate = updateDriverRequest.toModel();
        driverToUpdate.setId(driverId);

        Driver updatedDriver = driverService.updateDriver(driverToUpdate);

        DriverResponseDto updateDriverResponseBody = DriverResponseDto.fromModel(updatedDriver);

        return ResponseEntity.status(HttpStatus.OK).body(updateDriverResponseBody);
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity<Void> deleteDriverById(
            @PathVariable String driverId
    ) throws DriverNotFoundException {
        driverService.deleteById(driverId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/document/{driverDocument}")
    public ResponseEntity<Void> deleteDriverByDocument(
            @PathVariable String driverDocument
    ) throws DriverNotFoundException {
        driverService.deleteByDocument(driverDocument);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
