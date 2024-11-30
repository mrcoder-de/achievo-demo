package com.timetrack.actions;

import org.springframework.stereotype.Component;
import com.timetrack.domain.CostCenter;
import com.timetrack.domain.CostCenterActivity;
import java.util.List;

@Component
public class FetchAllActivitiesForCostCenter {

    public List<CostCenterActivity> execute(CostCenter costCenter) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}