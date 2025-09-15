package com.ridetogether.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetogether.user_service.dto.DriverDto;
import com.ridetogether.user_service.dto.PassengerDto;
import com.ridetogether.user_service.service.PassengerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PassengerController.class)
class PassengerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassengerService passengerService;

    @Autowired
    private ObjectMapper objectMapper;

    private PassengerDto passenger(Long id) {
        PassengerDto p = new PassengerDto();
        p.setId(id);
        p.setName("Passenger " + id);
        p.setEmail("passenger" + id + "@example.com");
        return p;
    }

    private DriverDto driver(Long id) {
        DriverDto d = new DriverDto();
        d.setId(id);
        d.setName("Driver " + id);
        d.setEmail("driver" + id + "@example.com");
        return d;
    }

    @Test
    @DisplayName("GET /users/passengers/{id} - returns 200 with passenger when found")
    void getPassengerById_found_returnsOk() throws Exception {
        Mockito.when(passengerService.findById(1L)).thenReturn(Optional.of(passenger(1L)));

        mockMvc.perform(get("/users/passengers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("passenger1@example.com"));
    }

    @Test
    @DisplayName("GET /users/passengers/{id} - returns 404 when not found")
    void getPassengerById_notFound_returns404() throws Exception {
        Mockito.when(passengerService.findById(404L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/passengers/404"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /users/passengers - returns list of passengers")
    void getAllPassengers_returnsList() throws Exception {
        List<PassengerDto> passengers = Arrays.asList(passenger(1L), passenger(2L));
        Mockito.when(passengerService.findAllPassengers()).thenReturn(passengers);

        mockMvc.perform(get("/users/passengers"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    @DisplayName("GET /users/passengers/{id}/matching-drivers - returns matching drivers")
    void getMatchingDrivers_returnsList() throws Exception {
        List<DriverDto> drivers = Arrays.asList(driver(10L), driver(20L));
        Mockito.when(passengerService.findMatchingDrivers(5L)).thenReturn(drivers);

        mockMvc.perform(get("/users/passengers/5/matching-drivers"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(10L))
                .andExpect(jsonPath("$[1].id").value(20L));
    }

    @Test
    @DisplayName("GET /users/passengers/{id}/matching-drivers - returns 404 when passenger not found")
    void getMatchingDrivers_passengerNotFound_returns404() throws Exception {
        Mockito.when(passengerService.findMatchingDrivers(anyLong()))
                .thenThrow(new jakarta.persistence.EntityNotFoundException("There is no passenger with id: 99"));

        mockMvc.perform(get("/users/passengers/99/matching-drivers"))
                .andExpect(status().isNotFound());
    }
}


