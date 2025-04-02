package com.blusaltdrone.service.impl;

import com.blusaltdrone.dtos.request.DroneRequestDto;
import com.blusaltdrone.dtos.request.MedicationRequestDto;
import com.blusaltdrone.dtos.response.PageResponse;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DroneServiceImpl implements DroneService {
    private final DroneRepository droneRepository;

    /**
     * Registers a new drone.
     * @param droneRequest drone registration data
     * @return registered drone entity
     */
    @Transactional
    @Override
    public Drone registerDrone(DroneRequestDto droneRequest) {
        log.info("Attempting to register new drone with serial number: {}", droneRequest.getSerialNumber());

        Drone drone = DroneMapper.toDrone(droneRequest);
        Drone savedDrone = droneRepository.save(drone);
        log.info("Successfully registered drone with ID: {}", savedDrone.getId());
        return savedDrone;
    }

    /**
     * Loads medications onto a drone.
     * @param droneId target drone ID
     * @param medications list of medications to load
     * @return updated drone entity
     * @throws BadRequestException if weight limit exceeded
     */
    @Transactional
    @Override
    public Drone loadDrone(Long droneId, List<MedicationRequestDto> medications) {
        log.info("Loading drone with ID: {} with {} medications", droneId, medications.size());

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

    /**
     * Gets medications loaded on a drone.
     * @param droneId target drone ID
     * @return list of loaded medications
     */
    @Override
    public List<Medication> getLoadedMedications(Long droneId) {
        log.info("Fetching loaded medications for drone ID: {}", droneId);

        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Drone not found"));
        return drone.getMedications();
    }

    /**
     * Gets available drones (IDLE state with ≥25% battery).
     * @param pageNo pagination page number
     * @param pageSize items per page
     * @return paginated response of available drones
     */
    @Override
    public  PageResponse<List<Drone>> getAvailableDrones(int pageNo, int pageSize) {
        log.info("Fetching available drones in IDLE state with battery of 25%)");
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        Page<Drone> dronePage = droneRepository.findByStateAndBatteryCapacityGreaterThanEqual(DroneState.IDLE, 25, pageable);
        return getDronePageResponse(dronePage);
    }

    /**
     * Gets all drones with pagination.
     * @param pageNo pagination page number
     * @param pageSize items per page
     * @return paginated response of all drones
     */
    @Override
    public PageResponse<List<Drone>> getDrones(int pageNo, int pageSize) {
        log.info("Fetching all drones");
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "id"));

        Page<Drone> dronePage = droneRepository.findAll(pageable);
        return getDronePageResponse(dronePage);
    }


    /**
     * Gets battery level for a drone.
     * @param droneId target drone ID
     * @return current battery percentage
     */
    @Override
    public int getBatteryLevel(Long droneId) {
        log.info("Fetching battery level for drone ID: {}", droneId);

        return droneRepository.findById(droneId)
                .map(Drone::getBatteryCapacity)
                .orElseThrow(() -> new ResourceNotFoundException("Battery limit exceeded"));
    }

    /**
     * Creates paginated response for drone queries.
     * @param dronePage page of drone results
     * @return formatted page response
     * @throws ResourceNotFoundException if no drones found
     */
    private PageResponse<List<Drone>> getDronePageResponse(Page<Drone> dronePage) {

        if (dronePage.isEmpty()) {
            throw new ResourceNotFoundException("No drones found");
        }
        return new PageResponse<>(
                dronePage.getNumberOfElements(),
                dronePage.getTotalPages(),
                dronePage.hasNext(),
                dronePage.getContent());
    }
}