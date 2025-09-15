package com.ridetogether.user_service.mapper;

import com.ridetogether.user_service.dto.DriverDto;
import com.ridetogether.user_service.model.User;

import static com.ridetogether.user_service.model.UserRole.DRIVER;

public class DriverDtoMapper {

    public static DriverDto toDto(User user) {
        if (user == null) return null;
        if (user.getRole() != DRIVER)
            throw new IllegalArgumentException("You are trying to map user that is passenger to driver");

        DriverDto dto = new DriverDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setHomeAddress(user.getHomeAddress());
        dto.setOfficeAddress(user.getOfficeAddress());
        dto.setPreferredArrivalStart(user.getPreferredArrivalStart());
        dto.setPreferredArrivalEnd(user.getPreferredArrivalEnd());
        dto.setFlexibilityMinutes(user.getFlexibilityMinutes());
        dto.setFlexibilityKm(user.getFlexibilityKm());
        dto.setRole(user.getRole());
        dto.setAvailableSeats(user.getAvailableSeats());
        dto.setCostPer100KmEUR(user.getCostPer100KmEUR());
        return dto;
    }
}