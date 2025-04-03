package com.blusaltdrone.controller;

import com.blusaltdrone.dtos.request.DroneRequestDto;
import com.blusaltdrone.enums.DroneModel;
import com.blusaltdrone.enums.DroneState;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(DispatchController.class)
class DispatchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testRegisterDrone() throws Exception {
        DroneRequestDto request = new DroneRequestDto("DR123", DroneModel.LIGHTWEIGHT, 400, 100, DroneState.IDLE);

        mockMvc.perform(post("/api/v1/drones/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.serialNumber").value("DR123"));
    }
}
