package com.blusaltdrone.utils;

import com.blusaltdrone.enums.DroneState;
import com.blusaltdrone.model.Drone;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class DroneUtils {

    public void doDroneCheck(Drone drone){
        if (drone.getBatteryCapacity() < 25) {
            throw new IllegalStateException("Drone battery too low to load medications");
        }
        if (drone.getState() != DroneState.IDLE) {
            throw new IllegalStateException("Drone is not in an IDLE state");
        }
    }

    private void logBatteryLevel(Drone drone) {
        log.info("Drone {} battery level: {}%",
                drone.getSerialNumber(), drone.getBatteryCapacity());
    }
}
