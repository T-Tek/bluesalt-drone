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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/drones")
@RequiredArgsConstructor
@Tag(name = "Drone API")
public class DispatchController {
    private final DroneService droneService;

    @PostMapping("/register")
    @Operation(summary = "Register a new drone")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    public ResponseEntity<Response> registerDrone(@Valid @RequestBody DroneRequestDto request) {
        Drone drone = droneService.registerDrone(request);
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, drone);
    }

    @PostMapping("/{id}/load")
    @Operation(summary = "Load medications onto drone")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "400", description = "Invalid input")
    @ApiResponse(responseCode = "404", description = "Drone not found")
    public ResponseEntity<Response> loadDrone(@PathVariable Long id, @Valid @RequestBody List<MedicationRequestDto> medications) {
        Drone drone = droneService.loadDrone(id, medications);
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, drone);
    }

    @GetMapping("/{id}/medications")
    @Operation(summary = "Get loaded medications")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Response> getLoadedMedications(@PathVariable Long id) {
        List<Medication> medications = droneService.getLoadedMedications(id);
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, medications);
    }

    @GetMapping("/available")
    @Operation(summary = "Get available drones")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<Response> getAvailableDrones(@RequestParam int pageNo, @RequestParam int pageSize) {
        PageResponse<List<Drone>> drones = droneService.getAvailableDrones(pageNo, pageSize);
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, drones);
    }

    @GetMapping("/{id}/battery")
    @Operation(summary = "Check drone battery level")
    @ApiResponse(responseCode = "200", description = "Success")
    @ApiResponse(responseCode = "404", description = "Not found")
    public ResponseEntity<Response> getBatteryLevel(@PathVariable Long id) {
        String batteryLevel = droneService.getBatteryLevel(id);
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, batteryLevel);
    }

    @GetMapping("/all")
    @Operation(summary = "Get all drones")
    @ApiResponse(responseCode = "200", description = "Success")
    public ResponseEntity<Response> getAll(@RequestParam int pageNo, @RequestParam int pageSize) {
        PageResponse<List<Drone>> drones = droneService.getDrones(pageNo, pageSize);
        return Utils.getResponse(ResponseCodeAndMessage.SUCCESS, drones);
    }
}