package com.blusaltdrone.utils;

import com.blusaltdrone.dtos.response.Response;
import com.blusaltdrone.enums.DroneState;
import com.blusaltdrone.enums.ResponseCodeAndMessage;
import com.blusaltdrone.model.Drone;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

@UtilityClass
@Slf4j
public class Utils {

    public void doDroneCheck(Drone drone){
        if (drone.getBatteryCapacity() < 25) {
            throw new IllegalStateException("Drone battery too low to load medications");
        }
        if (drone.getState() != DroneState.IDLE) {
            throw new IllegalStateException("Drone is not in an IDLE state");
        }
    }

    public static ResponseEntity<Response> getResponse(ResponseCodeAndMessage responseCodeAndMessage, Object data) {
        Response response = new Response(responseCodeAndMessage.status.value(), responseCodeAndMessage.name(), data);
        return new ResponseEntity<>(response, responseCodeAndMessage.status);
    }
}
