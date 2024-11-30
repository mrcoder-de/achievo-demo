package com.timetrack.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

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

    public List<CostCenterActivity> fetchAllActivitiesForCostCenter(CostCenter costCenter) {
        if (costCenter == null || costCenter.getCostCenterId() == null) {
            throw new IllegalArgumentException("Valid cost center is required");
        }
        return costCenterActivityRepository.findByCostCenter(costCenter);
    }

    public CostCenterActivity modifyCostCenterActivity(CostCenterActivity activity) {
        if (activity.getActivityId() == null) {
            throw new ActivityValidationException("Activity ID is required for modification");
        }

        CostCenterActivity existingActivity = costCenterActivityRepository.findById(activity.getActivityId())
                .orElseThrow(() -> new ActivityNotFoundException("Activity not found"));

        if (activity.getName() != null && !activity.getName().trim().isEmpty()) {
            existingActivity.setName(activity.getName().trim());
        }

        if (activity.getIsActive() != null) {
            existingActivity.setIsActive(activity.getIsActive());
        }

        return costCenterActivityRepository.save(existingActivity);
    }
}