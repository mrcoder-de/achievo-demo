package com.timetrack.actions;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.timetrack.domain.CostCenter;
import com.timetrack.domain.CostCenterService;

@Component
public class ModifyCostCenterDetails {

    @Autowired
    private CostCenterService costCenterService;

    public CostCenter execute(CostCenter costCenter) {
        return costCenterService.updateCostCenterDetails(costCenter);
    }
}