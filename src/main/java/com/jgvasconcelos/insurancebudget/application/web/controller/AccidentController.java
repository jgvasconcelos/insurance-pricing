package com.jgvasconcelos.insurancebudget.application.web.controller;

import com.jgvasconcelos.insurancebudget.application.web.dto.request.CreateAccidentRequestDto;
import com.jgvasconcelos.insurancebudget.application.web.dto.response.AccidentResponseDto;
import com.jgvasconcelos.insurancebudget.domain.model.Accident;
import com.jgvasconcelos.insurancebudget.domain.service.AccidentService;
import com.jgvasconcelos.insurancebudget.resources.repository.accident.exception.AccidentNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
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
@RequestMapping("/api/v1/accidents")
public class AccidentController {
    private final AccidentService accidentService;

    @PostMapping
    public ResponseEntity<AccidentResponseDto> createAccident(
            @Valid @RequestBody CreateAccidentRequestDto createAccidentRequest
    ) throws DriverNotFoundException, CarNotFoundException {
        Accident createdAccident = accidentService.create(createAccidentRequest.toModel());

        AccidentResponseDto accidentResponseBody = AccidentResponseDto.fromModel(createdAccident);

        return ResponseEntity.status(HttpStatus.CREATED).body(accidentResponseBody);
    }

    @GetMapping("/{accidentId}")
    public ResponseEntity<AccidentResponseDto> getAccidentById(
            @PathVariable String accidentId
    ) throws AccidentNotFoundException {
        Accident retrievedAccident = accidentService.getById(accidentId);

        AccidentResponseDto getAccidentResponseBody = AccidentResponseDto.fromModel(retrievedAccident);

        return ResponseEntity.status(HttpStatus.OK).body(getAccidentResponseBody);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<AccidentResponseDto>> getAccidentByDriverId(
            @PathVariable String driverId
    ) {
        List<Accident> retrievedAccidents = accidentService.getAllByDriverId(driverId);

        List<AccidentResponseDto> getAccidentsResponseBody =
                retrievedAccidents.stream().map(AccidentResponseDto::fromModel).toList();

        return ResponseEntity.status(HttpStatus.OK).body(getAccidentsResponseBody);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<AccidentResponseDto>> getAccidentByCarId(
            @PathVariable String carId
    ) {
        List<Accident> retrievedAccidents = accidentService.getAllByCarId(carId);

        List<AccidentResponseDto> getAccidentsResponseBody =
                retrievedAccidents.stream().map(AccidentResponseDto::fromModel).toList();

        return ResponseEntity.status(HttpStatus.OK).body(getAccidentsResponseBody);
    }

    @DeleteMapping("/{accidentId}")
    public ResponseEntity<AccidentResponseDto> deleteAccidentById(
            @PathVariable String accidentId
    ) throws AccidentNotFoundException {
        accidentService.delete(accidentId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
