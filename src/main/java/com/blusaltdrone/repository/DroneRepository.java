package com.blusaltdrone.repository;

import com.blusaltdrone.enums.DroneState;
import com.blusaltdrone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    Optional<Drone> findBySerialNumber(String serialNumber);
    List<Drone> findByState(DroneState state);

    List<Drone> findByStateAndBatteryCapacityGreaterThanEqual(DroneState droneState, int i);
}
