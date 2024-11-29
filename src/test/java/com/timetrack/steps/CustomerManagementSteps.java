package com.timetrack.steps;

import com.timetrack.actions.CreateNewCustomerWithRequiredInformation;
import com.timetrack.actions.ModifyExistingCustomerDetails;
import com.timetrack.domain.Customer;
import com.timetrack.domain.CustomerRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class CustomerManagementSteps {

    @Autowired
    private CreateNewCustomerWithRequiredInformation createNewCustomerAction;

    @Autowired
    private ModifyExistingCustomerDetails modifyExistingCustomerAction;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;
    private Exception thrownException;

    @Given("a new customer with name {string}, email {string}, phone {string}, and address {string}")
    public void aNewCustomerWithAllInformation(String name, String email, String phone, String address) {
        customer = new Customer();
        customer.setName(name);
        customer.setContactEmail(email);
        customer.setPhoneNumber(phone);
        customer.setBillingAddress(address);
    }

    @Given("a new customer with name {string} and email {string}")
    public void aNewCustomerWithPartialInformation(String name, String email) {
        customer = new Customer();
        customer.setName(name);
        customer.setContactEmail(email);
    }

    @When("the controller attempts to create the customer")
    public void theControllerAttemptsToCreateTheCustomer() {
        try {
            customer = createNewCustomerAction.execute(customer);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("the customer should be successfully created")
    public void theCustomerShouldBeSuccessfullyCreated() {
        assertNotNull(customer.getCustomerId());
        Customer savedCustomer = customerRepository.findById(customer.getCustomerId()).orElse(null);
        assertNotNull(savedCustomer);
        assertEquals(customer.getName(), savedCustomer.getName());
        assertEquals(customer.getContactEmail(), savedCustomer.getContactEmail());
        assertEquals(customer.getPhoneNumber(), savedCustomer.getPhoneNumber());
        assertEquals(customer.getBillingAddress(), savedCustomer.getBillingAddress());
    }

    @Then("a customer-management error should occur with the message {string}")
    public void aCustomerManagementErrorShouldOccurWithTheMessage(String errorMessage) {
        assertNotNull(thrownException);
        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Given("a customer {string} in the system")
    public void aCustomerInTheSystem(String customerName) {
        customer = new Customer();
        customer.setName(customerName);
        customer.setContactEmail("contact@" + customerName.toLowerCase().replace(" ", "") + ".com");
        customer.setPhoneNumber("1234567890");
        customer.setBillingAddress("123 Main St, City, Country");
        customer = customerRepository.save(customer);
    }

    @When("the controller updates the customer's name to {string}")
    public void theControllerUpdatesTheCustomerSNameTo(String newName) {
        try {
            customer.setName(newName);
            customer = modifyExistingCustomerAction.execute(customer);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("the customer's name should be changed to {string}")
    public void theCustomerSNameShouldBeChangedTo(String expectedName) {
        Customer updatedCustomer = customerRepository.findById(customer.getCustomerId()).orElse(null);
        assertNotNull(updatedCustomer);
        assertEquals(expectedName, updatedCustomer.getName());
    }
}