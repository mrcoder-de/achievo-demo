package com.timetrack.steps;

import com.timetrack.actions.CreateNewUserByAdmin;
import com.timetrack.domain.User;
import com.timetrack.domain.UserRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class UserManagementSteps {

    @Autowired
    private CreateNewUserByAdmin createNewUserByAdmin;

    @Autowired
    private UserRepository userRepository;

    private User user;
    private Exception thrownException;
    private User existingUser;

    @Given("a user with an invalid email")
    public void aUserWithAnInvalidEmail() {
        user = new User();
        user.setEmail("invalid-email");
        user.setFirstName("John");
        user.setLastName("Doe");
    }

    @Given("a user with a valid email")
    public void aUserWithAValidEmail() {
        user = new User();
        user.setEmail("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");
    }

    @Given("a user with an empty first name")
    public void aUserWithAnEmptyFirstName() {
        user = new User();
        user.setEmail("john.doe@example.com");
        user.setFirstName("");
        user.setLastName("Doe");
    }

    @Given("a user with an empty last name")
    public void aUserWithAnEmptyLastName() {
        user = new User();
        user.setEmail("john.doe@example.com");
        user.setFirstName("John");
        user.setLastName("");
    }

    @Given("an existing user")
    public void anExistingUser() {
        existingUser = new User();
        existingUser.setEmail("existing@example.com");
        existingUser.setFirstName("Existing");
        existingUser.setLastName("User");
        userRepository.save(existingUser);
    }

    @When("the admin attempts to create the user")
    public void theAdminAttemptsToCreateTheUser() {
        try {
            user = createNewUserByAdmin.execute(user);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("the admin attempts to create a new user with the same email as the existing user")
    public void theAdminAttemptsToCreateANewUserWithTheSameEmailAsTheExistingUser() {
        user = new User();
        user.setEmail(existingUser.getEmail());
        user.setFirstName("New");
        user.setLastName("User");
        try {
            user = createNewUserByAdmin.execute(user);
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("a user-management error should occur with the message {string}")
    public void aUserManagementErrorShouldOccurWithTheMessage(String errorMessage) {
        assertNotNull(thrownException);
        assertEquals(errorMessage, thrownException.getMessage());
    }

    @Then("the user should be successfully created")
    public void theUserShouldBeSuccessfullyCreated() {
        assertNotNull(user);
        assertNotNull(user.getEmail());
        User savedUser = userRepository.findById(user.getEmail()).orElse(null);
        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getFirstName(), savedUser.getFirstName());
        assertEquals(user.getLastName(), savedUser.getLastName());
    }
}