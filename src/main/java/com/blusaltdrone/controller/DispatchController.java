package com.blusaltdrone.controller;

import com.blusaltdrone.dtos.request.DroneRequestDto;
import com.blusaltdrone.dtos.request.MedicationRequestDto;
import com.blusaltdrone.dtos.response.PageResponse;
import com.blusaltdrone.dtos.response.Response;
import com.blusaltdrone.enums.ResponseCodeAndMessage;
import com.blusaltdrone.model.Drone;
import com.blusaltdrone.model.Medication;
import com.blusaltdrone.service.DroneService;
import com.blusaltdrone.utils.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drones")
@RequiredArgsConstructor
public class DispatchController {
    private final DroneService droneService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerDrone(@Valid @RequestBody DroneRequestDto request) {
        Drone drone = droneService.registerDrone(request);
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, drone);
    }

    @PostMapping("/{id}/load")
    public ResponseEntity<Response> loadDrone(@PathVariable Long id, @Valid @RequestBody List<MedicationRequestDto> medications) {
        Drone drone = droneService.loadDrone(id, medications);
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, drone);
    }

    @GetMapping("/{id}/medications")
    public ResponseEntity<Response> getLoadedMedications(@PathVariable Long id) {
        List<Medication> medications = droneService.getLoadedMedications(id);
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, medications);
    }

    @GetMapping("/available")
    public ResponseEntity<Response> getAvailableDrones() {
        List<Drone> drones = droneService.getAvailableDrones();
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, drones);
    }

    @GetMapping("/{id}/battery")
    public ResponseEntity<Response> getBatteryLevel(@PathVariable Long id) {
        int batteryLevel = droneService.getBatteryLevel(id);
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, batteryLevel);
    }

    @GetMapping("/all")
    public ResponseEntity<Response> getAll(@RequestParam int pageNo, @RequestParam int pageSize) {
        PageResponse<List<Drone>> drones = droneService.getDrones(pageNo, pageSize);
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, drones);
    }
}