package com.jgvasconcelos.insurancebudget.resources.repository.accident;

import com.jgvasconcelos.insurancebudget.domain.model.Accident;
import com.jgvasconcelos.insurancebudget.domain.repository.AccidentRepository;
import com.jgvasconcelos.insurancebudget.resources.repository.accident.dao.AccidentDao;
import com.jgvasconcelos.insurancebudget.resources.repository.accident.entity.AccidentEntity;
import com.jgvasconcelos.insurancebudget.resources.repository.accident.exception.AccidentNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@AllArgsConstructor
public class AccidentRepositoryImpl implements AccidentRepository {
    private final AccidentDao accidentDao;

    @Override
    public Accident add(Accident accident) {
        AccidentEntity accidentEntity = AccidentEntity.fromModel(accident);

        AccidentEntity savedAccident = accidentDao.save(accidentEntity);

        return savedAccident.toModel();
    }

    @Override
    public Accident getById(String accidentId) throws AccidentNotFoundException {
        Optional<AccidentEntity> optionalRetrievedAccidentEntity = accidentDao.findById(accidentId);

        return optionalRetrievedAccidentEntity.orElseThrow(
                () -> {
                    log.error("Accident with Id: [{}] was not found while trying to retrieve it.", accidentId);

                    return new AccidentNotFoundException("Accident with Id: [" + accidentId + "] was not found while trying to retrieve it.");
                }
        ).toModel();
    }

    @Override
    public List<Accident> getAllByCarId(String carId) {
        List<AccidentEntity> retrievedAccidents = accidentDao.findByCarId(carId);

        return retrievedAccidents.stream().map(AccidentEntity::toModel).toList();
    }

    @Override
    public List<Accident> getAllByDriverId(String driverId) {
        List<AccidentEntity> retrievedAccidents = accidentDao.findByDriverId(driverId);

        return retrievedAccidents.stream().map(AccidentEntity::toModel).toList();
    }

    @Override
    public void deleteById(String accidentId) throws AccidentNotFoundException {
        Integer deletedAccidents = accidentDao.deleteAccidentById(accidentId);

        if (deletedAccidents == 0) {
            log.error("Accident with Id: [{}] was not found while trying to delete it.", accidentId);

            throw new AccidentNotFoundException("Accident with Id [" + accidentId + "] was not found while trying to delete it.");
        }
    }
}
