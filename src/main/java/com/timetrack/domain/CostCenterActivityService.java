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
        return costCenterActivityRepository.save(activity);
    }
}