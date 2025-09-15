package com.ridetogether.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetogether.user_service.model.User;
import com.ridetogether.user_service.model.UserRole;
import com.ridetogether.user_service.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User sampleUser(Long id) {
        User u = new User();
        u.setId(id);
        u.setName("John Doe");
        u.setEmail("john@example.com");
        u.setPassword("StrongP@ss1");
        u.setPhone("+12345678901");
        u.setHomeAddress("Home");
        u.setOfficeAddress("Office");
        u.setPreferredArrivalStart(LocalTime.of(8, 0));
        u.setPreferredArrivalEnd(LocalTime.of(9, 0));
        u.setFlexibilityMinutes(10);
        u.setFlexibilityKm(2.5);
        u.setRole(UserRole.PASSENGER);
        u.setAvailableSeats(0);
        u.setCostPer100KmEUR(5.0);
        return u;
    }

    @Test
    @DisplayName("GET /users/{id} - returns 200 with user when found")
    void getUserById_found_returnsOk() throws Exception {
        Mockito.when(userService.findById(1L)).thenReturn(Optional.of(sampleUser(1L)));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.email").value("john@example.com"));
    }

    @Test
    @DisplayName("GET /users/{id} - returns 404 when not found")
    void getUserById_notFound_returns404() throws Exception {
        Mockito.when(userService.findById(404L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/404"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("GET /users - returns list of users")
    void getAllUsers_returnsList() throws Exception {
        List<User> users = Arrays.asList(sampleUser(1L), sampleUser(2L));
        Mockito.when(userService.findAll()).thenReturn(users);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
    }

    @Test
    @DisplayName("POST /users - creates and returns user")
    void createUser_returnsCreated() throws Exception {
        User toCreate = sampleUser(null);
        User created = sampleUser(10L);
        Mockito.when(userService.createUser(any(User.class))).thenReturn(created);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    @DisplayName("PUT /users/{id} - updates and returns user")
    void updateUser_returnsUpdated() throws Exception {
        User input = sampleUser(5L);
        input.setName("Updated");
        User updated = sampleUser(5L);
        updated.setName("Updated");
        Mockito.when(userService.updateUser(any(User.class))).thenReturn(updated);

        mockMvc.perform(put("/users/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    @Test
    @DisplayName("DELETE /users/{id} - deletes user and returns 204")
    void deleteUser_noContent() throws Exception {
        User toDelete = sampleUser(3L);
        Mockito.doNothing().when(userService).deleteUser(any(User.class));

        mockMvc.perform(delete("/users/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(toDelete)))
                .andExpect(status().isNoContent());
    }
}


