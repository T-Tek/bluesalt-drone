package com.blusaltdrone.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DroneBatteryInfo {
    private Long Id;
    private int batteryCapacity;
}
