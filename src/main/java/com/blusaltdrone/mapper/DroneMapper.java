package com.blusaltdrone.mapper;

import com.blusaltdrone.dtos.request.DroneRequestDto;
import com.blusaltdrone.model.Drone;

public class DroneMapper {

    public  static Drone toDrone(DroneRequestDto requestDto) {
        return  Drone.builder()
                .serialNumber(requestDto.getSerialNumber())
                .model(requestDto.getModel())
                .weightLimit(requestDto.getWeightLimit())
                .batteryCapacity(requestDto.getBatteryCapacity())
                .state(requestDto.getState())
                .build();
    }


}
