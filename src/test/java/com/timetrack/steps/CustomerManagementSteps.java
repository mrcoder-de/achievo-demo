package com.timetrack.steps;

import com.timetrack.actions.CreateNewCustomerWithRequiredInformation;
import com.timetrack.actions.ModifyExistingCustomerDetails;
import com.timetrack.actions.FetchFilterableListOfAllCustomers;
import com.timetrack.domain.Customer;
import com.timetrack.domain.CustomerRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CustomerManagementSteps {

    @Autowired
    private CreateNewCustomerWithRequiredInformation createNewCustomerAction;

    @Autowired
    private ModifyExistingCustomerDetails modifyExistingCustomerAction;

    @Autowired
    private FetchFilterableListOfAllCustomers fetchFilterableListOfAllCustomersAction;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;
    private Exception thrownException;
    private List<Customer> fetchedCustomers;

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

    @Given("there is no customer with ID {long} in the system")
    public void thereIsNoCustomerWithIDInTheSystem(Long customerId) {
        assertFalse(customerRepository.existsById(customerId));
    }

    @When("the controller attempts to modify the customer with ID {long}")
    public void theControllerAttemptsToModifyTheCustomerWithID(Long customerId) {
        try {
            Customer nonExistentCustomer = new Customer();
            nonExistentCustomer.setCustomerId(customerId);
            nonExistentCustomer.setName("Test Name");
            nonExistentCustomer.setContactEmail("test@example.com");
            nonExistentCustomer.setPhoneNumber("1234567890");
            nonExistentCustomer.setBillingAddress("Test Address");
            modifyExistingCustomerAction.execute(nonExistentCustomer);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Given("there are {int} customers in the system")
    public void thereAreCustomersInTheSystem(int customerCount) {
        customerRepository.deleteAll();
        for (int i = 0; i < customerCount; i++) {
            Customer newCustomer = new Customer();
            newCustomer.setName("Customer " + (i + 1));
            newCustomer.setContactEmail("customer" + (i + 1) + "@example.com");
            newCustomer.setPhoneNumber("123456789" + i);
            newCustomer.setBillingAddress("Address " + (i + 1));
            customerRepository.save(newCustomer);
        }
    }

    @When("the controller requests the list of all customers")
    public void theControllerRequestsTheListOfAllCustomers() {
        fetchedCustomers = fetchFilterableListOfAllCustomersAction.execute(null);
    }

    @Then("the controller should receive a list containing {int} customers")
    public void theControllerShouldReceiveAListContainingCustomers(int expectedCount) {
        assertNotNull(fetchedCustomers);
        assertEquals(expectedCount, fetchedCustomers.size());
    }
}