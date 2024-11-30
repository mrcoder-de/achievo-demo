package com.timetrack.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CostCenterRepository extends JpaRepository<CostCenter, Long> {
    List<CostCenter> findByNameContainingIgnoreCase(String partialName);
    List<CostCenter> findByIsActive(Boolean isActive);
    List<CostCenter> findByNameContainingIgnoreCaseAndIsActive(String partialName, Boolean isActive);
    List<CostCenter> findByNameIgnoreCase(String name);
}