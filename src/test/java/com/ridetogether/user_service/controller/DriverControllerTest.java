package com.ridetogether.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetogether.user_service.dto.DriverDto;
import com.ridetogether.user_service.dto.PassengerDto;
import com.ridetogether.user_service.service.DriverService;
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

@WebMvcTest(controllers = DriverController.class)
class DriverControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DriverService driverService;

    @Autowired
    private ObjectMapper objectMapper;

    private DriverDto driver(Long id) {
        DriverDto d = new DriverDto();
        d.setId(id);
        d.setName("Driver " + id);
        d.setEmail("driver" + id + "@example.com");
        return d;
    }

    private PassengerDto passenger(Long id) {
        PassengerDto p = new PassengerDto();
        p.setId(id);
        p.setName("Passenger " + id);
        p.setEmail("passenger" + id + "@example.com");
        return p;
    }

    @Test
    @DisplayName("GET /users/drivers/{id} - returns 200 with driver when found")
    void getDriverById_found_returnsOk() throws Exception {
        Mockito.when(driverService.findById(1L)).thenReturn(Optional.of(driver(1L)));

        mockMvc.perform(get("/users/drivers/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("driver1@example.com"));
    }

    @Test
    @DisplayName("GET /users/drivers/{id} - returns 404 when not found")
    void getDriverById_notFound_returns404() throws Exception {
        Mockito.when(driverService.findById(404L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/drivers/404"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /users/drivers - returns list of drivers")
    void getAllDrivers_returnsList() throws Exception {
        List<DriverDto> drivers = Arrays.asList(driver(1L), driver(2L));
        Mockito.when(driverService.findAllDrivers()).thenReturn(drivers);

        mockMvc.perform(get("/users/drivers"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    @DisplayName("GET /users/drivers/{id}/matching-drivers - returns matching passengers")
    void getMatchingPassengers_returnsList() throws Exception {
        List<PassengerDto> passengers = Arrays.asList(passenger(10L), passenger(20L));
        Mockito.when(driverService.findMatchingPassengers(5L)).thenReturn(passengers);

        mockMvc.perform(get("/users/drivers/5/matching-drivers"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(10L))
                .andExpect(jsonPath("$[1].id").value(20L));
    }

    @Test
    @DisplayName("GET /users/drivers/{id}/matching-drivers - returns 404 when driver not found")
    void getMatchingPassengers_driverNotFound_returns404() throws Exception {
        Mockito.when(driverService.findMatchingPassengers(anyLong()))
                .thenThrow(new jakarta.persistence.EntityNotFoundException("There is no driver with id: 99"));

        mockMvc.perform(get("/users/drivers/99/matching-drivers"))
                .andExpect(status().isNotFound());
    }
}


