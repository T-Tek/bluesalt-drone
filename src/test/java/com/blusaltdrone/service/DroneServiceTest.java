package com.blusaltdrone.service;

import com.blusaltdrone.dtos.request.DroneRequestDto;
import com.blusaltdrone.enums.DroneModel;
import com.blusaltdrone.enums.DroneState;
import com.blusaltdrone.model.Drone;
import com.blusaltdrone.repository.DroneRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DroneServiceTest {

    @InjectMocks
    private DroneService droneService;

    @Mock
    private DroneRepository droneRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterDrone() {
        DroneRequestDto request = new DroneRequestDto("DR123", DroneModel.LIGHTWEIGHT, 400, 100, DroneState.IDLE);
        Drone drone = Drone.builder()
                .id(1L)
                .serialNumber("DR123")
                .model(DroneModel.LIGHTWEIGHT)
                .weightLimit(400)
                .batteryCapacity(100)
                .state(DroneState.IDLE)
                .build();

        when(droneRepository.save(any(Drone.class))).thenReturn(drone);
        Drone result = droneService.registerDrone(request);
        assertNotNull(result);
        assertEquals("DR123", result.getSerialNumber());
        verify(droneRepository, times(1)).save(any(Drone.class));
    }
}
