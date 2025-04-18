package com.blusaltdrone.repository;

import com.blusaltdrone.enums.DroneState;
import com.blusaltdrone.model.Drone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DroneRepository extends JpaRepository<Drone, Long> {
    Optional<Drone> findById(Long id);
    Page<Drone> findByStateAndBatteryCapacityGreaterThanEqual(DroneState droneState, int i, Pageable pageable);
    Page<Drone> findAll(Pageable pageable);
}
