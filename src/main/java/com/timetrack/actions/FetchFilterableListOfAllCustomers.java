package com.timetrack.actions;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.timetrack.domain.Customer;
import com.timetrack.domain.CustomerService;
import java.util.List;

@Component
public class FetchFilterableListOfAllCustomers {

    @Autowired
    private CustomerService customerService;

    public List<Customer> execute(String nameFilter) {
        return customerService.fetchAllCustomers(nameFilter);
    }
}