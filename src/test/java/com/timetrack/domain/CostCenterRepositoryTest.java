package com.timetrack.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class CostCenterRepositoryTest {

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindById() {
        // Create a User for the manager
        User manager = new User();
        manager.setEmail("manager@example.com");
        manager.setFirstName("John");
        manager.setLastName("Doe");
        userRepository.save(manager);

        // Create a CostCenter
        CostCenter costCenter = new CostCenter();
        costCenter.setName("Test Cost Center");
        costCenter.setManager(manager);

        // Save the CostCenter
        CostCenter savedCostCenter = costCenterRepository.save(costCenter);

        // Retrieve the CostCenter
        CostCenter retrievedCostCenter = costCenterRepository.findById(savedCostCenter.getCostCenterId()).orElse(null);

        // Assert
        assertNotNull(retrievedCostCenter);
        assertEquals(savedCostCenter.getCostCenterId(), retrievedCostCenter.getCostCenterId());
        assertEquals(savedCostCenter.getName(), retrievedCostCenter.getName());
        assertEquals(savedCostCenter.getManager().getEmail(), retrievedCostCenter.getManager().getEmail());
    }
}