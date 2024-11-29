package com.timetrack.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class CustomerService {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final String PHONE_REGEX = "^\\+?\\d{10,14}$";
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    @Autowired
    private CustomerRepository customerRepository;

    public Customer createCustomer(Customer customer) {
        validateCustomer(customer);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        if (customer.getCustomerId() == null) {
            throw new IllegalArgumentException("Customer ID must be provided for update");
        }
        
        Customer existingCustomer = customerRepository.findById(customer.getCustomerId())
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found"));

        validateCustomer(customer);
        
        existingCustomer.setName(customer.getName());
        existingCustomer.setContactEmail(customer.getContactEmail());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        existingCustomer.setBillingAddress(customer.getBillingAddress());
        
        return customerRepository.save(existingCustomer);
    }

    public List<Customer> fetchAllCustomers() {
        return customerRepository.findAll();
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

        if (!isValidPhoneNumber(customer.getPhoneNumber())) {
            throw new InvalidPhoneNumberException("Invalid phone number");
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && PHONE_PATTERN.matcher(phoneNumber).matches();
    }
}