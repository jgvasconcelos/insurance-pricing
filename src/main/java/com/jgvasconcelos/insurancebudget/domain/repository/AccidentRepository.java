package com.jgvasconcelos.insurancebudget.domain.repository;

import com.jgvasconcelos.insurancebudget.domain.model.Accident;
import com.jgvasconcelos.insurancebudget.resources.repository.accident.exception.AccidentNotFoundException;

import java.util.List;

public interface AccidentRepository {
    Accident add(Accident accident);
    Accident getById(String accidentId) throws AccidentNotFoundException;
    List<Accident> getAllByCarId(String carId);
    List<Accident> getAllByDriverId(String driverId);
    void deleteById(String accidentId) throws AccidentNotFoundException;
}
