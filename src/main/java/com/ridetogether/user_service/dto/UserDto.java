package com.ridetogether.user_service.dto;

import com.ridetogether.user_service.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String homeAddress;
    private String officeAddress;
    private LocalTime preferredArrivalStart;
    private LocalTime preferredArrivalEnd;
    private int flexibilityMinutes;
    private double flexibilityKm;
    private UserRole role;

    // getters and setters
}
