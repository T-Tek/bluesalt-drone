package com.blusaltdrone.model;

import com.blusaltdrone.enums.DroneModel;
import com.blusaltdrone.enums.DroneState;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Drone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serialNumber;

    @Enumerated(EnumType.STRING)
    private DroneModel model;
    private int weightLimit;
    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    private DroneState state;
}
