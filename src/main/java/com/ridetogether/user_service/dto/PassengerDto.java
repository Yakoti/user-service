package com.ridetogether.user_service.dto;

import com.ridetogether.user_service.model.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
public class PassengerDto {
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
}
