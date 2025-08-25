package com.ridetogether.user_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridetogether.user_service.model.LoginRequest;
import com.ridetogether.user_service.model.RegisterRequest;
import com.ridetogether.user_service.model.UserRole;
import com.ridetogether.user_service.service.AuthService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("POST /auth/register - returns 200 with token when registration succeeds")
    void register_success_returnsToken() throws Exception {
        RegisterRequest req = new RegisterRequest();
        req.setName("Alice");
        req.setEmail("alice@example.com");
        req.setPassword("StrongP@ssw0rd");
        req.setPhone("+12345678901");
        req.setRole(UserRole.PASSENGER);

        Mockito.when(authService.register(any(RegisterRequest.class))).thenReturn("jwt-token");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string("jwt-token"));
    }

    @Test
    @DisplayName("POST /auth/register - returns 400 with validation messages on invalid input")
    void register_validationErrors_returnsBadRequest() throws Exception {
        RegisterRequest req = new RegisterRequest();
        // Missing name, invalid email, weak password, invalid phone, null role
        req.setEmail("bad-email");
        req.setPassword("weak");
        req.setPhone("123");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[?(@ =~ /.*name.*/)]").exists());
    }

    @Test
    @DisplayName("POST /auth/register - returns 400 when service throws IllegalArgumentException")
    void register_conflict_returnsBadRequest() throws Exception {
        RegisterRequest req = new RegisterRequest();
        req.setName("Alice");
        req.setEmail("alice@example.com");
        req.setPassword("StrongP@ssw0rd");
        req.setPhone("+12345678901");
        req.setRole(UserRole.DRIVER);

        Mockito.when(authService.register(any(RegisterRequest.class)))
                .thenThrow(new IllegalArgumentException("Email already in use"));

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already in use"));
    }

    @Test
    @DisplayName("POST /auth/login - returns 200 with token when credentials valid")
    void login_success_returnsToken() throws Exception {
        LoginRequest req = new LoginRequest("alice@example.com", "StrongP@ssw0rd");
        Mockito.when(authService.login(any(LoginRequest.class))).thenReturn("jwt-token");

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(content().string("jwt-token"));
    }

    @Test
    @DisplayName("POST /auth/login - returns 401 when service rejects credentials")
    void login_invalidCredentials_returnsUnauthorized() throws Exception {
        LoginRequest req = new LoginRequest("alice@example.com", "wrong");
        Mockito.when(authService.login(any(LoginRequest.class)))
                .thenThrow(new IllegalArgumentException("Invalid credentials"));

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isUnauthorized())
                .andExpect(content().string("Invalid credentials"));
    }

    @Test
    @DisplayName("GET /auth/test - returns a simple success string")
    void test_returnsOkString() throws Exception {
        mockMvc.perform(get("/auth/test").header(HttpHeaders.AUTHORIZATION, "Bearer some.jwt.token"))
                .andExpect(status().isOk())
                .andExpect(content().string("hey acces to auth/ endpoints works"));
    }
}


