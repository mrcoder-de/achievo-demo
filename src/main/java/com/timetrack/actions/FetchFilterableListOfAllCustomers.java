package com.timetrack.actions;

import org.springframework.stereotype.Component;
import com.timetrack.domain.Customer;
import java.util.List;

@Component
public class FetchFilterableListOfAllCustomers {

    public List<Customer> execute(String nameFilter) {
        throw new UnsupportedOperationException("Method not implemented yet");
    }
}