package com.jgvasconcelos.insurancebudget.application.web.controller;

import com.jgvasconcelos.insurancebudget.application.web.dto.request.CreateInsuranceBudgetRequestDto;
import com.jgvasconcelos.insurancebudget.application.web.dto.request.UpdateInsuranceBudgetRequestDto;
import com.jgvasconcelos.insurancebudget.application.web.dto.response.InsuranceBudgetResponseDto;
import com.jgvasconcelos.insurancebudget.domain.exception.DriverIsNotAssociatedWithCarException;
import com.jgvasconcelos.insurancebudget.domain.exception.DriverIsNotMainDriverOfCarException;
import com.jgvasconcelos.insurancebudget.domain.model.InsuranceBudget;
import com.jgvasconcelos.insurancebudget.domain.service.InsuranceBudgetService;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.exception.InsuranceBudgetNotFoundException;
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

import java.util.List;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/v1/insurance/budgets")
public class InsuranceBudgetController {
    private final InsuranceBudgetService insuranceBudgetService;

    @PostMapping
    public ResponseEntity<InsuranceBudgetResponseDto> createInsuranceBudget(
            @Valid @RequestBody CreateInsuranceBudgetRequestDto createInsuranceBudgetRequest
    ) throws DriverNotFoundException, CarNotFoundException, DriverIsNotAssociatedWithCarException, DriverIsNotMainDriverOfCarException {
        InsuranceBudget createdInsuranceBudget = insuranceBudgetService.createInsuranceBudget(createInsuranceBudgetRequest.toModel());

        InsuranceBudgetResponseDto insuranceBudgetResponseBody = InsuranceBudgetResponseDto.fromModel(createdInsuranceBudget);

        return ResponseEntity.status(HttpStatus.CREATED).body(insuranceBudgetResponseBody);
    }

    @GetMapping("/{insuranceBudgetId}")
    public ResponseEntity<InsuranceBudgetResponseDto> getInsuranceBudgetById(
            @PathVariable String insuranceBudgetId
    ) throws InsuranceBudgetNotFoundException {
        InsuranceBudget retrievedInsuranceBudget = insuranceBudgetService.getById(insuranceBudgetId);

        InsuranceBudgetResponseDto insuranceBudgetResponseBody = InsuranceBudgetResponseDto.fromModel(retrievedInsuranceBudget);

        return ResponseEntity.status(HttpStatus.OK).body(insuranceBudgetResponseBody);
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<List<InsuranceBudgetResponseDto>> getInsuranceBudgetBtDriverId(
            @PathVariable String driverId
    ) {
        List<InsuranceBudget> retrievedInsuranceBudget = insuranceBudgetService.getByDriverId(driverId);

        List<InsuranceBudgetResponseDto> insuranceBudgetResponseBody = retrievedInsuranceBudget.stream().map(InsuranceBudgetResponseDto::fromModel).toList();

        return ResponseEntity.status(HttpStatus.OK).body(insuranceBudgetResponseBody);
    }

    @GetMapping("/car/{carId}")
    public ResponseEntity<List<InsuranceBudgetResponseDto>> getInsuranceBudgetBtCarId(
            @PathVariable String carId
    ) {
        List<InsuranceBudget> retrievedInsuranceBudget = insuranceBudgetService.getByCarId(carId);

        List<InsuranceBudgetResponseDto> insuranceBudgetResponseBody = retrievedInsuranceBudget.stream().map(InsuranceBudgetResponseDto::fromModel).toList();

        return ResponseEntity.status(HttpStatus.OK).body(insuranceBudgetResponseBody);
    }

    @PutMapping("/{insuranceBudgetId}")
    public ResponseEntity<InsuranceBudgetResponseDto> updateInsuranceBudget(
            @PathVariable String insuranceBudgetId,
            @Valid @RequestBody UpdateInsuranceBudgetRequestDto updateInsuranceBudgetRequest
    ) throws InsuranceBudgetNotFoundException, DriverNotFoundException, CarNotFoundException {
        InsuranceBudget insuranceBudgetToUpdate = updateInsuranceBudgetRequest.toModel();
        insuranceBudgetToUpdate.setId(insuranceBudgetId);

        InsuranceBudget updatedInsuranceBudget = insuranceBudgetService.updateInsuranceBudget(insuranceBudgetToUpdate);

        InsuranceBudgetResponseDto insuranceBudgetResponseBody = InsuranceBudgetResponseDto.fromModel(updatedInsuranceBudget);

        return ResponseEntity.status(HttpStatus.OK).body(insuranceBudgetResponseBody);
    }

    @DeleteMapping("/{insuranceBudgetId}")
    public ResponseEntity<Void> deleteInsuranceBudgetById(
            @PathVariable String insuranceBudgetId
    ) throws InsuranceBudgetNotFoundException {
        insuranceBudgetService.deleteById(insuranceBudgetId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
