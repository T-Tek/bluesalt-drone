package com.blusaltdrone.dtos.request;

import com.blusaltdrone.enums.DroneModel;
import com.blusaltdrone.enums.DroneState;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DroneRequestDto {

    @NotBlank(message = "Serial number is required")
    @Size(max = 100, message = "Serial number cannot exceed 100 characters")
    private String serialNumber;

    @NotNull(message = "Drone model is required")
    private DroneModel model;

    @Min(value = 1, message = "Weight limit must be at least 1 gram")
    @Max(value = 500, message = "Weight limit cannot exceed 500 grams")
    private int weightLimit;

    @Min(value = 0, message = "Battery capacity cannot be negative")
    @Max(value = 100, message = "Battery capacity cannot exceed 100%")
    private int batteryCapacity;

    @NotNull(message = "Drone state is required")
    private DroneState state;
}
