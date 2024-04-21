package com.jgvasconcelos.insurancebudget.application.web.controller;

import com.jgvasconcelos.insurancebudget.application.web.dto.request.CreateCarRequestDto;
import com.jgvasconcelos.insurancebudget.application.web.dto.request.UpdateCarFipeValueRequestDto;
import com.jgvasconcelos.insurancebudget.application.web.dto.response.CarResponseDto;
import com.jgvasconcelos.insurancebudget.domain.model.car.Car;
import com.jgvasconcelos.insurancebudget.domain.service.car.CarService;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/cars")
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponseDto> createCar(
            @Valid @RequestBody CreateCarRequestDto createCarRequest
    ) {
        Car createdCar = carService.create(createCarRequest.toModel());

        CarResponseDto createCarResponseBody = CarResponseDto.fromModel(createdCar);

        return ResponseEntity.status(HttpStatus.CREATED).body(createCarResponseBody);
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarResponseDto> getCarById(
            @PathVariable String carId
    ) throws CarNotFoundException {
        Car retrievedCar = carService.getById(carId);

        CarResponseDto getCarResponseBody = CarResponseDto.fromModel(retrievedCar);

        return ResponseEntity.status(HttpStatus.OK).body(getCarResponseBody);
    }

    @PatchMapping("/{carId}")
    public ResponseEntity<CarResponseDto> updateFipeValueById(
            @PathVariable String carId,
            @Valid @RequestBody UpdateCarFipeValueRequestDto updateCarFipeValueRequest
    ) throws CarNotFoundException {
        Car updatedCar = carService.updateFipeValueById(carId, updateCarFipeValueRequest.getFipeValue());

        CarResponseDto responseBody = CarResponseDto.fromModel(updatedCar);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCarById(
            @PathVariable String carId
    ) throws CarNotFoundException {
        carService.deleteById(carId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
