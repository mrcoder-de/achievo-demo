package com.timetrack.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindById() {
        // Create a new User
        User user = new User();
        user.setEmail("test@example.com");
        user.setFirstName("John");
        user.setLastName("Doe");

        // Save the user
        User savedUser = userRepository.save(user);

        // Find the user by ID
        User foundUser = userRepository.findById(savedUser.getEmail()).orElse(null);

        // Assert that the user was found and has the correct properties
        assertNotNull(foundUser);
        assertEquals(savedUser.getEmail(), foundUser.getEmail());
        assertEquals(savedUser.getFirstName(), foundUser.getFirstName());
        assertEquals(savedUser.getLastName(), foundUser.getLastName());
        assertEquals(savedUser.getIsActive(), foundUser.getIsActive());
        assertNotNull(foundUser.getCreatedAt());
    }
}