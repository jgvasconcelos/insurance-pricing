package com.jgvasconcelos.insurancebudget.application.web.controller;

import com.jgvasconcelos.insurancebudget.application.web.dto.request.CreateCarDriverRequestDto;
import com.jgvasconcelos.insurancebudget.application.web.dto.response.CarDriverResponseDto;
import com.jgvasconcelos.insurancebudget.domain.model.CarDriver;
import com.jgvasconcelos.insurancebudget.domain.service.CarDriverService;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.cardriver.exception.CarDriverNotFoundException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/car-drivers")
public class CarDriverController {
    private final CarDriverService carDriverService;

    @PostMapping
    public ResponseEntity<CarDriverResponseDto> createCarDriver(
            @Valid @RequestBody CreateCarDriverRequestDto createCarDriverRequest
    ) throws DriverNotFoundException, CarNotFoundException {
        CarDriver createdCarDriver = carDriverService.create(
                createCarDriverRequest.getDriverId(),
                createCarDriverRequest.getCarId(),
                createCarDriverRequest.getIsMainDriver()
        );

        CarDriverResponseDto carDriverResponseBody = CarDriverResponseDto.fromModel(createdCarDriver);

        return ResponseEntity.status(HttpStatus.CREATED).body(carDriverResponseBody);
    }

    @GetMapping("/{carDriverId}")
    public ResponseEntity<CarDriverResponseDto> getCarDriverById(
            @PathVariable String carDriverId
    ) throws CarDriverNotFoundException {
        CarDriver retrievedCarDriver = carDriverService.getById(carDriverId);

        CarDriverResponseDto carDriverResponseBody = CarDriverResponseDto.fromModel(retrievedCarDriver);

        return ResponseEntity.status(HttpStatus.OK).body(carDriverResponseBody);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<CarDriverResponseDto>> getCarDriverByDriverId(
            @PathVariable String driverId
    ) {
        List<CarDriver> retrievedCarDrivers = carDriverService.getAllByDriverId(driverId);

        List<CarDriverResponseDto> carDriverResponseBody = retrievedCarDrivers.stream().map(CarDriverResponseDto::fromModel).toList();

        return ResponseEntity.status(HttpStatus.OK).body(carDriverResponseBody);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<CarDriverResponseDto>> getCarDriverByCarId(
            @PathVariable String carId
    ) {
        List<CarDriver> retrievedCarDrivers = carDriverService.getAllByCarId(carId);

        List<CarDriverResponseDto> carDriverResponseBody = retrievedCarDrivers.stream().map(CarDriverResponseDto::fromModel).toList();

        return ResponseEntity.status(HttpStatus.OK).body(carDriverResponseBody);
    }

    @DeleteMapping("/{carDriverId}")
    public ResponseEntity<CarDriverResponseDto> deleteCarById(
            @PathVariable String carDriverId
    ) throws CarDriverNotFoundException {
        carDriverService.deleteById(carDriverId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
