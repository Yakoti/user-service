package com.ridetogether.user_service.dto;

import com.ridetogether.user_service.model.UserRole;

import java.time.LocalTime;

public class PassengerDto extends UserDto{

    public PassengerDto(Long id, String name,String phone, String email, String homeAddress, String officeAddress,
                     LocalTime preferredArrivalStart, LocalTime preferredArrivalEnd,
                     int flexibilityMinutes, double flexibilityKm, UserRole role) {
        super(id, name, email, phone, homeAddress, officeAddress, preferredArrivalStart, preferredArrivalEnd,
                flexibilityMinutes, flexibilityKm, role);
    }
}
