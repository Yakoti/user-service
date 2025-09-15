package com.ridetogether.user_service.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

//TODO: add validation for adresses


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must have at least 8 chars, one uppercase, one lowercase, one digit, and one special char")
    private String password;

    @Pattern(regexp = "\\+?[0-9]{10,15}", message = "Invalid phone number")
    private String phone;

    @NotBlank(message = "Home address is required")
    private String homeAddress;

    @NotBlank(message = "Office address is required")
    private String officeAddress;

    @NotNull(message = "Preferred arrival start time is required")
    private LocalTime preferredArrivalStart;

    @NotNull(message = "Preferred arrival end time is required")
    private LocalTime preferredArrivalEnd;

    @Min(value = 0, message = "Flexibility minutes cannot be negative")
    private int flexibilityMinutes;

    @DecimalMin(value = "0.0", message = "Flexibility km cannot be negative")
    private double flexibilityKm;

    @NotNull(message = "Role is required")
    private UserRole role;

    @Min(value = 0, message = "Available seats cannot be negative")
    private int availableSeats;

    @DecimalMin(value = "0.0", message = "Cost per 100km cannot be negative")
    private double costPer100KmEUR;
}