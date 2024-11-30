package com.timetrack.steps;

import com.timetrack.actions.CreateCostCenterWithNameAndManager;
import com.timetrack.actions.FetchAndFilterCostCenters;
import com.timetrack.domain.CostCenter;
import com.timetrack.domain.CostCenterRepository;
import com.timetrack.domain.User;
import com.timetrack.domain.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CostManagementSteps {

    @Autowired
    private CreateCostCenterWithNameAndManager createCostCenterAction;

    @Autowired
    private FetchAndFilterCostCenters fetchAndFilterCostCentersAction;

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Autowired
    private UserRepository userRepository;

    private CostCenter costCenter;
    private Exception thrownException;
    private List<CostCenter> fetchedCostCenters;

    @Given("a new cost center with name {string} and manager {string}")
    public void aNewCostCenterWithNameAndManager(String name, String managerEmail) {
        User manager = new User();
        manager.setEmail(managerEmail);
        manager.setFirstName("John"); // Placeholder first name
        manager.setLastName("Doe"); // Placeholder last name
        userRepository.save(manager);

        costCenter = new CostCenter();
        costCenter.setName(name);
        costCenter.setManager(manager);
    }

    @Given("a new cost center with manager {string} and no name")
    public void aNewCostCenterWithManagerAndNoName(String managerEmail) {
        User manager = new User();
        manager.setEmail(managerEmail);
        manager.setFirstName("John"); // Placeholder first name
        manager.setLastName("Doe"); // Placeholder last name
        userRepository.save(manager);

        costCenter = new CostCenter();
        costCenter.setManager(manager);
    }

    @Given("a new cost center with name {string} and no manager")
    public void aNewCostCenterWithNameAndNoManager(String name) {
        costCenter = new CostCenter();
        costCenter.setName(name);
    }

    @Given("a new cost center with name {string} with manager {string}")
    public void aNewCostCenterWithNameWithManager(String name, String managerEmail) {
        User manager = new User();
        manager.setEmail(managerEmail);
        manager.setFirstName("Jane"); // Placeholder first name
        manager.setLastName("Doe"); // Placeholder last name
        userRepository.save(manager);

        costCenter = new CostCenter();
        costCenter.setName(name);
        costCenter.setManager(manager);
    }

    @When("the cost center is created")
    public void theCostCenterIsCreated() {
        try {
            costCenter = createCostCenterAction.execute(costCenter);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("the cost center should be created successfully")
    public void theCostCenterShouldBeCreatedSuccessfully() {
        assertNotNull(costCenter.getCostCenterId());
        CostCenter savedCostCenter = costCenterRepository.findById(costCenter.getCostCenterId()).orElse(null);
        assertNotNull(savedCostCenter);
        assertEquals(costCenter.getName(), savedCostCenter.getName());
        assertEquals(costCenter.getManager().getEmail(), savedCostCenter.getManager().getEmail());
    }

    @Then("a cost-center-management error should occur with the message {string}")
    public void aCostCenterManagementErrorShouldOccurWithTheMessage(String errorMessage) {
        assertNotNull(thrownException);
        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Then("the cost center's status should be set to {string}")
    public void theCostCenterStatusShouldBeSetTo(String status) {
        assertNotNull(costCenter.getCostCenterId());
        CostCenter savedCostCenter = costCenterRepository.findById(costCenter.getCostCenterId()).orElse(null);
        assertNotNull(savedCostCenter);
        assertEquals("active".equals(status), savedCostCenter.getIsActive());
    }

    @Given("there are multiple cost centers in the system")
    public void thereAreMultipleCostCentersInTheSystem() {
        User manager1 = new User();
        manager1.setEmail("manager1@example.com");
        manager1.setFirstName("Manager");
        manager1.setLastName("One");
        userRepository.save(manager1);

        User manager2 = new User();
        manager2.setEmail("manager2@example.com");
        manager2.setFirstName("Manager");
        manager2.setLastName("Two");
        userRepository.save(manager2);

        CostCenter costCenter1 = new CostCenter();
        costCenter1.setName("Cost Center 1");
        costCenter1.setManager(manager1);
        costCenterRepository.save(costCenter1);

        CostCenter costCenter2 = new CostCenter();
        costCenter2.setName("Cost Center 2");
        costCenter2.setManager(manager2);
        costCenterRepository.save(costCenter2);
    }

    @When("the controller requests the list of all cost centers")
    public void theControllerRequestsTheListOfAllCostCenters() {
        fetchedCostCenters = fetchAndFilterCostCentersAction.execute(null, null);
    }

    @Then("the system should return a list containing all cost centers")
    public void theSystemShouldReturnAListContainingAllCostCenters() {
        assertNotNull(fetchedCostCenters);
        assertFalse(fetchedCostCenters.isEmpty());
        assertEquals(costCenterRepository.count(), fetchedCostCenters.size());
    }

    @Given("there are no cost centers in the system")
    public void thereAreNoCostCentersInTheSystem() {
        costCenterRepository.deleteAll();
    }

    @Then("the system should return an empty list of cost centers")
    public void theSystemShouldReturnAnEmptyListOfCostCenters() {
        assertNotNull(fetchedCostCenters);
        assertTrue(fetchedCostCenters.isEmpty());
    }

    @Given("there are cost centers named {string}, {string}, and {string}")
    public void thereAreCostCentersNamed(String name1, String name2, String name3) {
        User manager = new User();
        manager.setEmail("manager@example.com");
        manager.setFirstName("Manager");
        manager.setLastName("Test");
        userRepository.save(manager);

        List<String> names = Arrays.asList(name1, name2, name3);
        for (String name : names) {
            CostCenter costCenter = new CostCenter();
            costCenter.setName(name);
            costCenter.setManager(manager);
            costCenterRepository.save(costCenter);
        }
    }

    @When("the controller filters the list with the partial name {string}")
    public void theControllerFiltersTheListWithThePartialName(String partialName) {
        fetchedCostCenters = fetchAndFilterCostCentersAction.execute(partialName, null);
    }

    @Then("the system should return a list containing only the {string} cost center")
    public void theSystemShouldReturnAListContainingOnlyTheCostCenter(String expectedCostCenterName) {
        assertNotNull(fetchedCostCenters);
        assertEquals(1, fetchedCostCenters.size());
        assertEquals(expectedCostCenterName, fetchedCostCenters.get(0).getName());
    }

    @Given("there are both active and inactive cost centers in the system")
    public void thereAreBothActiveAndInactiveCostCentersInTheSystem() {
        User manager = new User();
        manager.setEmail("manager@example.com");
        manager.setFirstName("Manager");
        manager.setLastName("Test");
        userRepository.save(manager);

        CostCenter activeCostCenter = new CostCenter();
        activeCostCenter.setName("Active Cost Center");
        activeCostCenter.setManager(manager);
        activeCostCenter.setIsActive(true);
        costCenterRepository.save(activeCostCenter);

        CostCenter inactiveCostCenter = new CostCenter();
        inactiveCostCenter.setName("Inactive Cost Center");
        inactiveCostCenter.setManager(manager);
        inactiveCostCenter.setIsActive(false);
        costCenterRepository.save(inactiveCostCenter);
    }

    @When("the controller filters the list for active cost centers")
    public void theControllerFiltersTheListForActiveCostCenters() {
        fetchedCostCenters = fetchAndFilterCostCentersAction.execute(null, true);
    }

    @When("the controller filters the list for inactive cost centers")
    public void theControllerFiltersTheListForInactiveCostCenters() {
        fetchedCostCenters = fetchAndFilterCostCentersAction.execute(null, false);
    }

    @Then("the system should return a list containing only the active cost centers")
    public void theSystemShouldReturnAListContainingOnlyTheActiveCostCenters() {
        assertNotNull(fetchedCostCenters);
        assertFalse(fetchedCostCenters.isEmpty());
        for (CostCenter costCenter : fetchedCostCenters) {
            assertTrue(costCenter.getIsActive());
        }
    }

    @Then("the system should return a list containing only the inactive cost centers")
    public void theSystemShouldReturnAListContainingOnlyTheInactiveCostCenters() {
        assertNotNull(fetchedCostCenters);
        assertFalse(fetchedCostCenters.isEmpty());
        for (CostCenter costCenter : fetchedCostCenters) {
            assertFalse(costCenter.getIsActive());
        }
    }
}