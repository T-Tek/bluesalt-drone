package com.blusaltdrone.service.impl;

import com.blusaltdrone.dtos.request.DroneRequestDto;
import com.blusaltdrone.dtos.request.MedicationRequestDto;
import com.blusaltdrone.enums.DroneState;
import com.blusaltdrone.exceptions.BadRequestException;
import com.blusaltdrone.exceptions.ResourceNotFoundException;
import com.blusaltdrone.mapper.DroneMapper;
import com.blusaltdrone.mapper.MedicationMapper;
import com.blusaltdrone.model.Drone;
import com.blusaltdrone.model.Medication;
import com.blusaltdrone.repository.DroneRepository;
import com.blusaltdrone.service.DroneService;
import com.blusaltdrone.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;

    @Transactional
    @Override
    public Drone registerDrone(DroneRequestDto droneRequest) {
        Drone drone = DroneMapper.toDrone(droneRequest);
        return droneRepository.save(drone);
    }

    @Transactional
    @Override
    public Drone loadDrone(Long droneId, List<MedicationRequestDto> medications) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));

        Utils.doDroneCheck(drone);

        int totalWeight = 0;
        for (MedicationRequestDto medication : medications) {
            totalWeight += medication.getWeight();
        }

        if (totalWeight > drone.getWeightLimit()) {
            throw new BadRequestException("Drone weight limit exceeded");
        }

        List<Medication> medicationList = new ArrayList<>();
        for (MedicationRequestDto request : medications) {
            Medication medication = MedicationMapper.toMedication(request);
            medication.setDrone(drone);
            medicationList.add(medication);
        }

        drone.setState(DroneState.LOADED);
        drone.setMedications(medicationList);
        return droneRepository.save(drone);
    }

    @Override
    public List<Medication> getLoadedMedications(Long droneId) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));
        return drone.getMedications();
    }

    @Override
    public List<Drone> getAvailableDrones() {
        return droneRepository.findByStateAndBatteryCapacityGreaterThanEqual(
                DroneState.IDLE, 25);
    }

    @Override
    public List<Drone> getDrones() {
        return droneRepository.findAll();
    }

    @Override
    public int getBatteryLevel(Long droneId) {
        return droneRepository.findById(droneId)
                .map(Drone::getBatteryCapacity)
                .orElseThrow(() -> new ResourceNotFoundException("Battery limit exceeded"));
    }




}