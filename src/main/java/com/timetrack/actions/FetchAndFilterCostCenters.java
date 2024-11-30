package com.timetrack.actions;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.timetrack.domain.CostCenter;
import com.timetrack.domain.CostCenterService;
import java.util.List;

@Component
public class FetchAndFilterCostCenters {

    @Autowired
    private CostCenterService costCenterService;

    public List<CostCenter> execute(String partialName, Boolean isActive) {
        return costCenterService.fetchAllCostCenters(partialName, isActive);
    }
}