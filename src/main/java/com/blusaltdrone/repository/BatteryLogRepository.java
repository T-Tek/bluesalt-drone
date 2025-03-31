package com.blusaltdrone.repository;

import com.blusaltdrone.model.BatteryAuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatteryLogRepository extends JpaRepository<BatteryAuditLog, Long> {
    List<BatteryAuditLog> findByDroneIdOrderByCheckTimeDesc(Long droneId);
}

