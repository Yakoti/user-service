package com.ridetogether.user_service.controller;


import com.ridetogether.user_service.model.LoginRequest;
import com.ridetogether.user_service.model.RegisterRequest;
import com.ridetogether.user_service.model.User;
import com.ridetogether.user_service.repository.UserRepository;
import com.ridetogether.user_service.service.AuthService;
import com.ridetogether.user_service.service.JwtService;
import com.ridetogether.user_service.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO: logout controller maybe not needed?


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

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
        //map validation errors to a simple error response
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(e -> e.getField() + ": " + e.getDefaultMessage())
                    .toList();
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            String token = authService.register(request);
            return ResponseEntity.ok(token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try{
            String token = authService.login(request);
            return ResponseEntity.ok(token);
        }catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/test")
    public String testAuth(){
        return "hey acces to auth/ endpoints works";
    }

//    @GetMapping("/login")
//    public ResponseEntity<?> loginBrowser(
//            @RequestParam String email,
//            @RequestParam String password) {
//        var userOpt = userService.findByEmail(email);
//        if (userOpt.isEmpty()) {
//            System.out.println("User not found for email: " + email);
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//
//        User user = userOpt.get();
//        boolean matches = passwordEncoder.matches(password, user.getPassword());
//        System.out.println("Password matches: " + matches);
//        if (!matches) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//        }
//        String token = jwtService.generateToken(user);
//        return ResponseEntity.ok(token);
//    }


}
