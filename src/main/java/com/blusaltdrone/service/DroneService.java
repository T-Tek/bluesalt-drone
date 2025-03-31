package com.blusaltdrone.service;

import com.blusaltdrone.dtos.DroneRequestDto;
import com.blusaltdrone.dtos.MedicationRequestDto;
import com.blusaltdrone.model.Drone;
import com.blusaltdrone.model.Medication;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DroneService {

    @Transactional
    Drone registerDrone(DroneRequestDto droneRequest);

    @Transactional
    Drone loadDrone(Long droneId, List<MedicationRequestDto> medications);

    List<Medication> getLoadedMedications(Long droneId);

    List<Drone> getAvailableDrones();

    int getBatteryLevel(Long droneId);
}
