package com.timetrack.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        validateCustomer(customer);
        return customerRepository.save(customer);
    }

    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty() ||
            customer.getContactEmail() == null || customer.getContactEmail().trim().isEmpty() ||
            customer.getPhoneNumber() == null || customer.getPhoneNumber().trim().isEmpty() ||
            customer.getBillingAddress() == null || customer.getBillingAddress().trim().isEmpty()) {
            throw new MissingRequiredFieldsException("Missing required fields");
        }
    }
}