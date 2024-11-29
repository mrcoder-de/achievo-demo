package com.timetrack.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testSaveAndFindById() {
        // Create a new Customer
        Customer customer = new Customer();
        customer.setName("Test Customer");
        customer.setContactEmail("test@example.com");
        customer.setPhoneNumber("1234567890");
        customer.setBillingAddress("123 Test St, Test City, TS 12345");
        customer.setCreatedAt(LocalDateTime.now());

        // Save the customer
        Customer savedCustomer = customerRepository.save(customer);

        // Ensure the customer was saved with an ID
        assertNotNull(savedCustomer.getCustomerId());

        // Find the customer by ID
        Customer foundCustomer = customerRepository.findById(savedCustomer.getCustomerId()).orElse(null);

        // Assert that the found customer is not null and has the same properties as the saved customer
        assertNotNull(foundCustomer);
        assertEquals(savedCustomer.getCustomerId(), foundCustomer.getCustomerId());
        assertEquals(savedCustomer.getName(), foundCustomer.getName());
        assertEquals(savedCustomer.getContactEmail(), foundCustomer.getContactEmail());
        assertEquals(savedCustomer.getPhoneNumber(), foundCustomer.getPhoneNumber());
        assertEquals(savedCustomer.getBillingAddress(), foundCustomer.getBillingAddress());
        assertEquals(savedCustomer.getCreatedAt(), foundCustomer.getCreatedAt());
    }
}