package com.timetrack.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CostCenterActivityRepository extends JpaRepository<CostCenterActivity, Long> {
    List<CostCenterActivity> findByCostCenter(CostCenter costCenter);
}