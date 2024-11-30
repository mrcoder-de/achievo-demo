package com.timetrack.steps;

import com.timetrack.actions.CreateCostCenterWithNameAndManager;
import com.timetrack.domain.CostCenter;
import com.timetrack.domain.CostCenterRepository;
import com.timetrack.domain.User;
import com.timetrack.domain.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CostManagementSteps {

    @Autowired
    private CreateCostCenterWithNameAndManager createCostCenterAction;

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Autowired
    private UserRepository userRepository;

    private CostCenter costCenter;
    private Exception thrownException;

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
}