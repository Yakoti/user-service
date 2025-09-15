package com.ridetogether.user_service.mapper;

import com.ridetogether.user_service.dto.PassengerDto;
import com.ridetogether.user_service.model.User;

import java.util.InputMismatchException;

import static com.ridetogether.user_service.model.UserRole.PASSENGER;


public class PassengerDtoMapper {

    public static PassengerDto toDto(User user) throws IllegalArgumentException{
        if (user == null) return null;
        if (user.getRole() != PASSENGER) throw new IllegalArgumentException("You are trying to map user that is driver to passenger");

        PassengerDto dto = new PassengerDto();
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
        return dto;
    }
}