package com.jgvasconcelos.insurancebudget.domain.service;

import com.jgvasconcelos.insurancebudget.domain.model.Accident;
import com.jgvasconcelos.insurancebudget.resources.repository.accident.exception.AccidentNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.car.exception.CarNotFoundException;
import com.jgvasconcelos.insurancebudget.resources.repository.driver.exception.DriverNotFoundException;

import java.util.List;

public interface AccidentService {
    Accident create(Accident accident) throws DriverNotFoundException, CarNotFoundException;
    Accident getById(String accidentId) throws AccidentNotFoundException;
    List<Accident> getAllByDriverId(String driverId);
    List<Accident> getAllByCarId(String cardId);
    void delete(String accidentId) throws AccidentNotFoundException;
}
