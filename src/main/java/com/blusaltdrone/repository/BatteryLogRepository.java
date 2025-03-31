package com.blusaltdrone.repository;

import com.blusaltdrone.model.BatteryLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatteryLogRepository extends JpaRepository<BatteryLog, Long> {
    List<BatteryLog> findByDroneId(Long droneId);
}

