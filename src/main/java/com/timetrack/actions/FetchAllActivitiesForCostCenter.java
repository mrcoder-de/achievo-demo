package com.timetrack.actions;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.timetrack.domain.CostCenter;
import com.timetrack.domain.CostCenterActivity;
import com.timetrack.domain.CostCenterActivityService;
import java.util.List;

@Component
public class FetchAllActivitiesForCostCenter {

    @Autowired
    private CostCenterActivityService costCenterActivityService;

    public List<CostCenterActivity> execute(CostCenter costCenter) {
        return costCenterActivityService.fetchAllActivitiesForCostCenter(costCenter);
    }
}