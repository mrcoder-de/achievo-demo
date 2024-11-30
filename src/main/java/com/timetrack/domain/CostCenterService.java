package com.timetrack.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class CostCenterService {

    @Autowired
    private CostCenterRepository costCenterRepository;

    @Autowired
    private UserRepository userRepository;

    public CostCenter createCostCenter(CostCenter costCenter) {
        if (costCenter.getName() == null || costCenter.getName().trim().isEmpty()) {
            throw new CostCenterValidationException("Cost center name is required");
        }
        if (costCenter.getManager() == null) {
            throw new CostCenterValidationException("Cost center manager is required");
        }
        
        // Ensure the cost center is set to active by default
        costCenter.setIsActive(true);
        
        return costCenterRepository.save(costCenter);
    }

    public List<CostCenter> fetchAllCostCenters(String partialName, Boolean isActive) {
        if (partialName == null || partialName.trim().isEmpty()) {
            if (isActive == null) {
                return costCenterRepository.findAll();
            } else {
                return costCenterRepository.findByIsActive(isActive);
            }
        }
        if (isActive == null) {
            return costCenterRepository.findByNameContainingIgnoreCase(partialName.trim());
        }
        return costCenterRepository.findByNameContainingIgnoreCaseAndIsActive(partialName.trim(), isActive);
    }

    public CostCenter updateCostCenterDetails(CostCenter updatedCostCenter) {
        CostCenter existingCostCenter = costCenterRepository.findById(updatedCostCenter.getCostCenterId())
                .orElseThrow(() -> new CostCenterNotFoundException("Cost center not found"));

        if (updatedCostCenter.getName() != null && !updatedCostCenter.getName().trim().isEmpty()) {
            existingCostCenter.setName(updatedCostCenter.getName().trim());
        }

        if (updatedCostCenter.getManager() != null) {
            User manager = userRepository.findById(updatedCostCenter.getManager().getEmail())
                    .orElseThrow(() -> new UserNotFoundException("Manager not found"));
            existingCostCenter.setManager(manager);
        }

        if (updatedCostCenter.getIsActive() != null) {
            existingCostCenter.setIsActive(updatedCostCenter.getIsActive());
        }

        return costCenterRepository.save(existingCostCenter);
    }
}