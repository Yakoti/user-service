package com.ridetogether.user_service.service;

import com.ridetogether.user_service.mapper.RegistrationRequestMapper;
import com.ridetogether.user_service.model.LoginRequest;
import com.ridetogether.user_service.model.RegisterRequest;
import com.ridetogether.user_service.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String register(RegisterRequest request) {
        if (userService.findByEmail(request.getEmail()).isPresent()) throw new IllegalArgumentException("EMAIL_INVALID: Account with this email already exists");

        User user = RegistrationRequestMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userService.createUser(user);

        return jwtService.generateToken(user);
    }

    public String login(LoginRequest request){
        User user = userService.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new IllegalArgumentException("Invalid credentials");

        return jwtService.generateToken(user);
    }
}
