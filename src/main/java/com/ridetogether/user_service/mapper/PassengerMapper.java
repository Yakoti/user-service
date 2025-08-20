package com.ridetogether.user_service.mapper;


import com.ridetogether.user_service.dto.PassengerDto;
import com.ridetogether.user_service.model.Passenger;

public class PassengerMapper {
    public static PassengerDto toDto(Passenger passenger) {
        return new PassengerDto(
                passenger.getId(),
                passenger.getName(),
                passenger.getEmail(),
                passenger.getPhone(),
                passenger.getHomeAddress(),
                passenger.getOfficeAddress(),
                passenger.getPreferredArrivalStart(),
                passenger.getPreferredArrivalEnd(),
                passenger.getFlexibilityMinutes(),
                passenger.getFlexibilityKm(),
                passenger.getRole()
        );
    }

}
