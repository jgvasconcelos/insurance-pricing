package com.jgvasconcelos.insurancebudget.domain.service.implementation;

import com.jgvasconcelos.insurancebudget.domain.model.Customer;
import com.jgvasconcelos.insurancebudget.domain.repository.CustomerRepository;
import com.jgvasconcelos.insurancebudget.domain.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        log.info("Creating a new customer with Name: [{}].", customer.getName());

        Customer createdCustomer = customerRepository.add(customer);

        log.info("Successfully created a new customer with Id: [{}] and Name: [{}].", createdCustomer.getId(), createdCustomer.getName());

        return createdCustomer;
    }

    @Override
    public Customer getById(String customerId) {
        log.info("Retrieving customer with Id: [{}].", customerId);

        Customer retrievedCustomer = customerRepository.getById(customerId);

        log.info("Successfully retrieved customer with Id: [{}] and Name: [{}].", retrievedCustomer.getId(), retrievedCustomer.getName());

        return retrievedCustomer;
    }

    @Override
    public Customer getByDocument(String customerDocument) {
        log.info("Retrieving customer with Document: [{}].", customerDocument);

        Customer retrievedCustomer = customerRepository.getByDocument(customerDocument);

        log.info("Successfully retrieved customer with Id: [{}] and Name: [{}].", retrievedCustomer.getId(), retrievedCustomer.getName());

        return retrievedCustomer;
    }

    @Override
    @Transactional
    public Customer updateCustomer(Customer customer) {
        log.info("Updating customer with Id: [{}].", customer.getId());

        Customer updatedCustomer = customerRepository.updateCustomer(customer);

        log.info("Successfully updated customer with Id: [{}] and Name: [{}].", updatedCustomer.getId(), updatedCustomer.getName());

        return updatedCustomer;
    }

    @Override
    @Transactional
    public void deleteById(String customerId) {
        log.info("Deleting customer with Id: [{}].", customerId);

        customerRepository.deleteById(customerId);

        log.info("Successfully deleted customer with Id: [{}].", customerId);
    }
}
