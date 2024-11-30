package com.timetrack.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CostCenterActivityService {

    @Autowired
    private CostCenterActivityRepository costCenterActivityRepository;

    public CostCenterActivity createCostCenterActivity(CostCenter costCenter, CostCenterActivity activity) {
        if (activity.getName() == null || activity.getName().trim().isEmpty()) {
            throw new ActivityValidationException("Activity name is required");
        }
        
        activity.setCostCenter(costCenter);
        
        // Ensure the activity is set to active by default
        if (activity.getIsActive() == null) {
            activity.setIsActive(true);
        }
        
        return costCenterActivityRepository.save(activity);
    }
}