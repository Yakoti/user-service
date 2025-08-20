package com.ridetogether.user_service.dto;

import com.ridetogether.user_service.model.UserRole;

import java.time.LocalTime;

public class DriverDto extends UserDto{
    private int availableSeats;
    private double costPer100KmEUR;

    public DriverDto(Long id, String name, String phone, String email, String homeAddress, String officeAddress,
                  LocalTime preferredArrivalStart, LocalTime preferredArrivalEnd,
                  int flexibilityMinutes, double flexibilityKm, UserRole role, int availableSeats, double costPer100KmEUR) {
        super(id, name, email, phone, homeAddress, officeAddress, preferredArrivalStart, preferredArrivalEnd,
                flexibilityMinutes, flexibilityKm, role);
        this.availableSeats = availableSeats;
        this.costPer100KmEUR = costPer100KmEUR;
    }
}
