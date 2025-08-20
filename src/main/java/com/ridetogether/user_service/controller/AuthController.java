package com.ridetogether.user_service.controller;


import com.ridetogether.user_service.model.LoginRequest;
import com.ridetogether.user_service.model.RegisterRequest;
import com.ridetogether.user_service.model.User;
import com.ridetogether.user_service.repository.UserRepository;
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

//TODO: logout controller maybe not needed?


@RestController
@RequestMapping("/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository, UserService userService,
                          PasswordEncoder passwordEncoder,
                          JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request, BindingResult result) {
        logger.info("Register endpoint request: " + request.toString());
        logger.info("Register endpoint request result: " + result.toString());

        // 1) Check if email is taken
        if (userService.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("EMAIL_INVALID " + "Account with this email: " + request.getEmail() + " already exists");
        }

        //2) Handle different invalid inputs
        /*
            one option is to return a JSON
            static class ErrorResponse {
                private String errorCode;
                private String message;
         */
        if (result.hasErrors()) {
            for (FieldError error : result.getFieldErrors()) {
                String field = error.getField();
                String msg = error.getDefaultMessage();
                switch (field) {
                    case "email":
                        return ResponseEntity.badRequest()
                                .body("EMAIL_INVALID " + msg);
                    case "password":
                        return ResponseEntity.badRequest()
                                .body("PASSWORD_INVALID " + msg);
                    case "phone":
                        return ResponseEntity.badRequest()
                                .body("PHONE_INVALID " + msg);
                    case "flexibilityMinutes":
                        return ResponseEntity.badRequest()
                                .body("FLEXIBILITY_MINUTES_INVALID " + msg);
                    case "flexibilityKm":
                        return ResponseEntity.badRequest()
                                .body("FLEXIBILITY_KM_INVALID " + msg);
                    case "role":
                        return ResponseEntity.badRequest()
                                .body("ROLE_INVALID " + msg);
                }
            }
            return ResponseEntity.badRequest()
                    .body("VALIDATION_ERROR Invalid register request");
        }
        User user = userService.registerNewUser(request);
        logger.info("User parsed from request: " + user.toString());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        logger.info("Login endpoint request: " + request.toString());

        if (userService.findByEmail(request.getEmail()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        User user = userService.findByEmail(request.getEmail()).get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = jwtService.generateToken(user);

        logger.info("Login endpoint generated token: " + token.toString() + " for user: " + user.getName());
        return ResponseEntity.ok(token);
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
