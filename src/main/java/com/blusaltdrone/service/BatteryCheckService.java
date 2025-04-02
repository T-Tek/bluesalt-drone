package com.blusaltdrone.service;

import com.blusaltdrone.model.BatteryAuditLog;
import com.blusaltdrone.model.Drone;
import com.blusaltdrone.repository.BatteryLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class BatteryCheckService {
    private final BatteryLogRepository auditLogRepository;

    public void logBatteryLevel(Drone drone) {
        BatteryAuditLog logEntry = BatteryAuditLog.builder()
                .id(drone.getId())
                .drone(drone)
                .batteryLevel(drone.getBatteryCapacity())
                .timestamp(LocalDateTime.now())
                .build();

        auditLogRepository.save(logEntry);

        if (drone.getBatteryCapacity() < 20) {
            log.warn("Low battery alert for drone {} (ID: {}): {}%",
                    drone.getSerialNumber(),
                    drone.getId(),
                    drone.getBatteryCapacity());
        }
    }
}