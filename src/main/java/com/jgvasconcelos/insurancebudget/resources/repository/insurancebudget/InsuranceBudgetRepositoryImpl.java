package com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget;

import com.jgvasconcelos.insurancebudget.domain.model.InsuranceBudget;
import com.jgvasconcelos.insurancebudget.domain.repository.InsuranceBudgetRepository;
import com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.dao.InsuranceBudgetDao;
import com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.entity.InsuranceBudgetEntity;
import com.jgvasconcelos.insurancebudget.resources.repository.insurancebudget.exception.InsuranceBudgetNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@AllArgsConstructor
public class InsuranceBudgetRepositoryImpl implements InsuranceBudgetRepository {
    private final InsuranceBudgetDao insuranceBudgetDao;

    @Override
    public InsuranceBudget add(InsuranceBudget insuranceBudget) {
        InsuranceBudgetEntity insuranceBudgetEntity = InsuranceBudgetEntity.fromModel(insuranceBudget);

        InsuranceBudgetEntity createdInsuranceBudget = insuranceBudgetDao.save(insuranceBudgetEntity);

        return createdInsuranceBudget.toModel();
    }

    @Override
    public InsuranceBudget getById(String insuranceBudgetId) throws InsuranceBudgetNotFoundException {
        Optional<InsuranceBudgetEntity> optionalRetrievedInsuranceBudgetEntity = insuranceBudgetDao.findById(insuranceBudgetId);

        return optionalRetrievedInsuranceBudgetEntity.orElseThrow(
                () -> {
                    log.error("Insurance budget with Id: [{}] was not found while trying to retrieve it.", insuranceBudgetId);

                    return new InsuranceBudgetNotFoundException("Insurance budget with Id: [" + insuranceBudgetId + "] was not found while trying to retrieve it.");
                }
        ).toModel();
    }

    @Override
    public List<InsuranceBudget> getByDriverId(String driverId) {
        List<InsuranceBudgetEntity> retrievedInsuranceBudgets = insuranceBudgetDao.findByDriverId(driverId);

        return retrievedInsuranceBudgets.stream().map(InsuranceBudgetEntity::toModel).toList();
    }

    @Override
    public List<InsuranceBudget> getByCarId(String carId) {
        List<InsuranceBudgetEntity> retrievedInsuranceBudgets = insuranceBudgetDao.findByCarId(carId);

        return retrievedInsuranceBudgets.stream().map(InsuranceBudgetEntity::toModel).toList();
    }

    @Override
    public InsuranceBudget updateInsuranceBudget(InsuranceBudget insuranceBudget) {
        InsuranceBudgetEntity insuranceBudgetEntity = InsuranceBudgetEntity.fromModel(insuranceBudget);

        InsuranceBudgetEntity updatedInsuranceBudget = insuranceBudgetDao.save(insuranceBudgetEntity);

        return updatedInsuranceBudget.toModel();
    }

    @Override
    public void deleteById(String insuranceBudgetId) throws InsuranceBudgetNotFoundException {
        Integer deletedInsuranceBudgets = insuranceBudgetDao.deleteInsuranceBudgetById(insuranceBudgetId);

        if (deletedInsuranceBudgets == 0) {
            log.error("Insurance budget with Id: [{}] was not found while trying to delete it.", insuranceBudgetId);

            throw new InsuranceBudgetNotFoundException("Insurance budget with Id: [" + insuranceBudgetId + "] was not found while trying to delete it.");
        }
    }
}
