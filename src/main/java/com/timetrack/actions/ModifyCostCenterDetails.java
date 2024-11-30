package com.timetrack.actions;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.timetrack.domain.CostCenter;
import com.timetrack.domain.CostCenterService;
import com.timetrack.domain.CostCenterNameAlreadyExistsException;

@Component
public class ModifyCostCenterDetails {

    @Autowired
    private CostCenterService costCenterService;

    public CostCenter execute(CostCenter costCenter) throws CostCenterNameAlreadyExistsException {
        try {
            return costCenterService.updateCostCenterDetails(costCenter);
        } catch (CostCenterNameAlreadyExistsException e) {
            throw new CostCenterNameAlreadyExistsException("Cost center name already exists");
        }
    }
}