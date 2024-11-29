package com.timetrack.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

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

        if (!isValidEmail(customer.getContactEmail())) {
            throw new InvalidCustomerEmailException("Invalid customer email address");
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}