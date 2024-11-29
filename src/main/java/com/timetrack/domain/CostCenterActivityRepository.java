package com.timetrack.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostCenterActivityRepository extends JpaRepository<CostCenterActivity, Long> {
}