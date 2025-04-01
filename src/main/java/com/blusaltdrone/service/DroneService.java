package com.blusaltdrone.service;

import com.blusaltdrone.dtos.request.DroneRequestDto;
import com.blusaltdrone.dtos.request.MedicationRequestDto;
import com.blusaltdrone.dtos.response.PageResponse;
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

    PageResponse<List<Drone>> getAvailableDrones(int pageNo, int pageSize);

    PageResponse<List<Drone>> getDrones(int pageNo, int pageSize);

    int getBatteryLevel(Long droneId);
}
