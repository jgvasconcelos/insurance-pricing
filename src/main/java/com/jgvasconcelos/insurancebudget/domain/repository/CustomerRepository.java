package com.jgvasconcelos.insurancebudget.domain.repository;

import com.jgvasconcelos.insurancebudget.domain.model.Customer;

public interface CustomerRepository {
    Customer add(Customer customer);
    Customer getById(String customerId);
    Customer getByDocument(String document);
    Customer updateCustomer(Customer customer);
    void deleteById(String customerId);
}
