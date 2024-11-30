package com.timetrack.actions;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.timetrack.domain.CostCenterActivity;
import com.timetrack.domain.CostCenterActivityService;

@Component
public class ModifyCostCenterActivity {

    @Autowired
    private CostCenterActivityService costCenterActivityService;

    public CostCenterActivity execute(CostCenterActivity activity) {
        return costCenterActivityService.modifyCostCenterActivity(activity);
    }
}