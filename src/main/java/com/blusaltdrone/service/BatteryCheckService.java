package com.blusaltdrone.service;

import com.blusaltdrone.model.BatteryAuditLog;
import com.blusaltdrone.model.Drone;
import com.blusaltdrone.repository.BatteryLogRepository;
import com.blusaltdrone.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BatteryCheckService {
    private final DroneRepository droneRepository;
    private final BatteryLogRepository auditLogRepository;

    @Scheduled(fixedRateString = "${drone.battery.check.interval:3600000}")
    public void checkAllDronesBattery() {
        List<Drone> drones = droneRepository.findAll();

        for (Drone drone : drones) {
            logBatteryLevel(drone);
        }

        log.info("Completed battery check for {} drones", drones.size());
    }

    private void logBatteryLevel(Drone drone) {
        LocalDateTime localDateTime = LocalDateTime.now();
        BatteryAuditLog logEntry = new BatteryAuditLog(drone.getId(), drone, drone.getBatteryCapacity(), localDateTime);
        auditLogRepository.save(logEntry);

        if (drone.getBatteryCapacity() < 20) {
            log.warn("Low battery alert for drone {}: {}%",
                    drone.getSerialNumber(), drone.getBatteryCapacity());
        }
    }
}