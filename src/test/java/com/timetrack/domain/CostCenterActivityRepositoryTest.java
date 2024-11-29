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
public class CostCenterActivityRepositoryTest {

    @Autowired
    private CostCenterActivityRepository costCenterActivityRepository;

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Test
    public void testSaveAndFindById() {
        // Create a CostCenter
        CostCenter costCenter = new CostCenter();
        costCenter.setName("Test Cost Center");
        CostCenter savedCostCenter = costCenterRepository.save(costCenter);

        // Create a CostCenterActivity
        CostCenterActivity activity = new CostCenterActivity();
        activity.setName("Test Activity");
        activity.setCostCenter(savedCostCenter);

        // Save the activity
        CostCenterActivity savedActivity = costCenterActivityRepository.save(activity);

        // Retrieve the activity
        CostCenterActivity retrievedActivity = costCenterActivityRepository.findById(savedActivity.getActivityId()).orElse(null);

        // Assert
        assertNotNull(retrievedActivity);
        assertEquals(savedActivity.getActivityId(), retrievedActivity.getActivityId());
        assertEquals("Test Activity", retrievedActivity.getName());
        assertEquals(savedCostCenter.getCostCenterId(), retrievedActivity.getCostCenter().getCostCenterId());
    }
}