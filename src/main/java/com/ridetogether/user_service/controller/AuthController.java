package com.ridetogether.user_service.controller;

import com.ridetogether.user_service.model.LoginRequest;
import com.ridetogether.user_service.model.RegisterRequest;
import com.ridetogether.user_service.service.AuthService;
import com.ridetogether.user_service.service.JwtService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(AuthService authService,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.authService = authService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .toList();

            logger.warn("Registration validation failed: {}", errors);
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            String token = authService.register(request);
            logger.info("User registered successfully: {}", request.getEmail());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            logger.error("Registration failed for {}: {}", request.getEmail(), e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        logger.info("Login attempt for {}", request.getEmail());
        try {
            String token = authService.login(request);
            logger.info("Login successful for {}", request.getEmail());
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            logger.warn("Login failed for {}: {}", request.getEmail(), e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/test")
    public String testAuth() {
        logger.debug("Test endpoint accessed");
        return "hey acces to auth/ endpoints works";
    }
}
