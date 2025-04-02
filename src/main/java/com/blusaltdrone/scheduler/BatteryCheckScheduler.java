package com.blusaltdrone.scheduler;

import com.blusaltdrone.model.Drone;
import com.blusaltdrone.repository.DroneRepository;
import com.blusaltdrone.service.BatteryCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class BatteryCheckScheduler {
    private final DroneRepository droneRepository;
    private final BatteryCheckService batteryCheckService;

    @Scheduled(fixedRateString = "${drone.battery.check.interval:3600000}")
    public void performBatteryCheck() {
        List<Drone> drones = droneRepository.findAll();
        drones.forEach(batteryCheckService::logBatteryLevel);
        log.info("Battery check completed for {} drones", drones.size());
    }
}