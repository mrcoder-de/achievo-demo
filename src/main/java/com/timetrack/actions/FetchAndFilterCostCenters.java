package com.timetrack.actions;

import org.springframework.stereotype.Component;
import com.timetrack.domain.CostCenter;
import java.util.List;

@Component
public class FetchAndFilterCostCenters {

    public List<CostCenter> execute(String partialName, Boolean isActive) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}