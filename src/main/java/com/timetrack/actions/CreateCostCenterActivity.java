package com.timetrack.actions;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.timetrack.domain.CostCenter;
import com.timetrack.domain.CostCenterActivity;
import com.timetrack.domain.CostCenterActivityService;

@Component
public class CreateCostCenterActivity {

    @Autowired
    private CostCenterActivityService costCenterActivityService;

    public CostCenterActivity execute(CostCenter costCenter, CostCenterActivity activity) {
        return costCenterActivityService.createCostCenterActivity(costCenter, activity);
    }
}