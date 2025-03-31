package com.blusaltdrone.mapper;

import com.blusaltdrone.dtos.request.MedicationRequestDto;
import com.blusaltdrone.model.Medication;

public class MedicationMapper {
    public static Medication toMedication(MedicationRequestDto requestDto) {
        return Medication.builder()
                .name(requestDto.getName())
                .code(requestDto.getCode())
                .image(requestDto.getImage())
                .build();
    }
}
