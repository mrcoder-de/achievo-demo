package com.timetrack.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CostCenterService {

    @Autowired
    private CostCenterRepository costCenterRepository;

    public CostCenter createCostCenter(CostCenter costCenter) {
        if (costCenter.getName() == null || costCenter.getName().trim().isEmpty()) {
            throw new CostCenterValidationException("Cost center name is required");
        }
        if (costCenter.getManager() == null) {
            throw new CostCenterValidationException("Cost center manager is required");
        }
        return costCenterRepository.save(costCenter);
    }
}