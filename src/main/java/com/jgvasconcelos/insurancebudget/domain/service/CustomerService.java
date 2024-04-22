package com.jgvasconcelos.insurancebudget.domain.service;

import com.jgvasconcelos.insurancebudget.domain.model.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer getById(String customerId);
    Customer getByDocument(String customerDocument);
    Customer updateCustomer(Customer customer);
    void deleteById(String customerId);
}
