package com.timetrack.actions;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.timetrack.domain.Customer;
import com.timetrack.domain.CustomerService;

@Component
public class ModifyExistingCustomerDetails {

    @Autowired
    private CustomerService customerService;

    public Customer execute(Customer customer) {
        return customerService.updateCustomer(customer);
    }
}