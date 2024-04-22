package com.jgvasconcelos.insurancebudget.resources.repository.customer;

import com.jgvasconcelos.insurancebudget.domain.model.Customer;
import com.jgvasconcelos.insurancebudget.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@AllArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer add(Customer customer) {
        return null;
    }

    @Override
    public Customer getById(String customerId) {
        return null;
    }

    @Override
    public Customer getByDocument(String document) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public void deleteById(String customerId) {

    }
}
